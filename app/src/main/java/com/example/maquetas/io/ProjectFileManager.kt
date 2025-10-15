package com.example.maquetas.io

import android.util.Log
import com.example.maquetas.models.Project
import kotlinx.serialization.StringFormat
import java.io.File
import java.io.FileWriter
import java.io.OutputStreamWriter

class ProjectFileManager(private val project: Project): FileManager() {

    override fun save(){

        if(!project.filePath.exists()){
            project.filePath.mkdir()
        }

        val configString=format()
        val path=project.filePath
        val child="${project.objectName}.data"
        val configFile= File(path,child)


        try
        {
            val fileWriter = configFile.writer()
            fileWriter.write(configString)
            fileWriter.close()
            Log.println(Log.DEBUG,"Config String",configString)


        }catch (e:Exception){
            e.printStackTrace()
        }

        //save tracks
        try{

            for(track in project.trackList){
                track.save()
            }

        }catch(e:Exception){
            e.printStackTrace()
        }

    }
    override fun load(): Project{
        return project
    }

    private fun format(): String{
        val configString=ConfigString()
        configString.addKey(key="name",value=project.projectName)
        for(i in project.trackList.indices){
            configString.addKey(key= "track$i",project.trackList[i].trackName)
        }
        configString.addKey("fav",project.isFav.toString())

        return configString.value

    }



}