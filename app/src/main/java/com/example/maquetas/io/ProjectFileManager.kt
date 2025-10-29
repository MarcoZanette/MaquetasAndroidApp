package com.example.maquetas.io

import android.util.Log
import com.example.maquetas.models.Project
import com.example.maquetas.models.ProjectObject
import com.example.maquetas.models.Track
import kotlinx.serialization.StringFormat
import java.io.File
import java.io.FileWriter
import java.io.OutputStreamWriter
import java.net.ProtocolException

class ProjectFileManager(private val project: Project=Project()): FileManager() {

    override fun save(){

        if(!project.filePath.exists()){
            project.filePath.mkdir()
        }

        val dataString=project.getDataString()
        val path=project.filePath
        val child="${project.objectName}.data"
        val configFile= File(path,child)


        try
        {
            val fileWriter = configFile.writer()
            fileWriter.write(dataString.value)
            fileWriter.close()
            Log.println(Log.DEBUG,"Config String",dataString.value)


        }catch (e:Exception){
            e.printStackTrace()
        }

        //save tracks TODO borrar tracks que fueron eliminadas por el usuario, comparando los dataStrings
        try{

            for(track in project.trackList){
                track.save()
            }

        }catch(e:Exception){
            e.printStackTrace()
        }

    }
    override fun load(dir:File): Project{//TODO Cargar proyecto

        var p=Project()
        try{
            p= readDataFile(dir)!!
        }
        catch (e:Exception){
            e.printStackTrace()
        }






        return p
    }

    override fun readDataFile(dir:File): Project?{

        val dataFile=File("$dir/${dir.name}.data")

        if(dataFile.exists()){

            val reader=dataFile.reader()
            val dataString=ConfigString(reader.readText())

            var name:String=""
            val trackList= mutableListOf<Track>()
            var isFav: Boolean=false
            //Agregar trackList y IsFav

            try
            {
                name = dataString.search("name")!!

                //TrackList
                var i=0
                var t="track$i"
                while(dataString.search(t)!=null){

                    val track=Track(trackName = dataString.search(t)!!, filePath = File("$dir/$t"))

                    trackList.add(track)

                    i++
                    t="track$i"
                }

                if(dataString.search("isFav")!=null){
                    isFav=dataString.search("isFav").toBoolean()
                }else isFav=false



            }catch (e:Exception){
                e.printStackTrace()
                Log.println(Log.DEBUG,"DataString",dataString.value)
            }

            val p= Project(name,dir,name)
            p.trackList=trackList
            p.isFav=isFav

            return p
        }
        else
        {
            return null
        }
    }


    private fun format(): String{//TODO BORRAR METODO
        val configString=ConfigString()
        configString.addKey(key="name",value=project.projectName)
        for(i in project.trackList.indices){
            configString.addKey(key= "track$i",project.trackList[i].trackName)
        }
        configString.addKey("fav",project.isFav.toString())

        return configString.value

    }



}