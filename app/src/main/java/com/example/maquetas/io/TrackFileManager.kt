package com.example.maquetas.io

import android.util.Log
import com.example.maquetas.models.ProjectObject
import com.example.maquetas.models.Take
import com.example.maquetas.models.Track
import java.io.File

class TrackFileManager(val track: Track): FileManager() {//esta clase se debe ocupar UNICAMENTE de la interaccion entre la clase Track y el sistema de archivos de android

    var trackDir: File = track.filePath

    override fun save() {
        saveConfig()
    }

    override fun readDataFile(dir: File): Take? {
        TODO("Not yet implemented")
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
        return track//TODO
    }


    fun getTrackList():MutableList<Take> {

        val temp = emptyList<Take>()
        return temp.toMutableList()//TODO leer la lista de takes-si no existe retornar la lista vacia
    }

}