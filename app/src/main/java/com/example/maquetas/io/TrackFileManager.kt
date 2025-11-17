package com.example.maquetas.io

import android.content.Context
import android.util.Log
import com.example.maquetas.models.Project
import com.example.maquetas.models.ProjectObject
import com.example.maquetas.models.Take
import com.example.maquetas.models.Track
import java.io.File
import java.io.FileNotFoundException

class TrackFileManager(val track: Track=Track()): FileManager() {//esta clase se debe ocupar UNICAMENTE de la interaccion entre la clase Track y el sistema de archivos de android



    var trackDir: File = track.filePath

    override fun save() {
        saveConfig()
    }


    fun saveConfig(){

        if (!track.filePath.exists()){
            track.filePath.mkdir()
        }


        val dataString=track.getDataString()
        val path=track.filePath
        val child="${track.trackName}.data"
        val configFile=File(path,child)

        try
        {
            val fileWriter = configFile.writer()
            fileWriter.write(dataString.value)
            fileWriter.close()
            Log.println(Log.DEBUG,"Config String",dataString.value)


        }catch (e:Exception){
            e.printStackTrace()
        }



    }


    override fun load(dir:File): Track{
        var t=readDataFile(dir)

        try {
            {t!!}
        }
        catch (e:Exception){
            e.printStackTrace()
            val ex=FileNotFoundException("No se encuentra el archivo .data en $dir")
            throw ex
        }


        return t!!
    }

    override fun readDataFile(
        dir: File,
        context: Context
    ): Track{
        TODO("Not yet implemented")
    }

    fun readDataFile(dir:File):Track?{
        val dataFile=File("$dir/${dir.name}.data")

        if(dataFile.exists()){

            val reader=dataFile.reader()
            val dataString=ConfigString(reader.readText())

            var name:String=""
            val takeList= mutableListOf<Take>()
            var isFav: Boolean=false
            //Agregar trackList y IsFav

            try
            {
                name = dataString.search("trackName")!!

                //TakeList
                var i=0
                var take="take$i"
                while(dataString.search(take)!=null){

                    val t=Take(takeName=dataString.search(take)!!, parentPath = File("$dir"))

                    takeList.add(t)

                    i++
                    take="track$i"
                }

                if(dataString.search("isFav")!=null){
                    isFav=dataString.search("isFav").toBoolean()
                }else isFav=false



            }catch (e:Exception){
                e.printStackTrace()
                Log.println(Log.DEBUG,"DataString",dataString.value)
            }

            val t= Track(name,dir)
            t.takeList=takeList


            return t
        }
        else
        {
            return null
        }
    }


    fun getTakeList():MutableList<Take> {

        val temp = emptyList<Take>()
        return temp.toMutableList()//TODO leer la lista de takes-si no existe retornar la lista vacia
    }

}