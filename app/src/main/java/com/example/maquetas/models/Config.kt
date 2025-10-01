package com.example.maquetas.models

import android.content.Context
import java.io.File

class Config(context: Context) {

    val defaultProjectsDir:File=File(context.filesDir.toString() + "projects")

    val configFile=File(context.filesDir.toString()+"config")

    var projectsDir=defaultProjectsDir


    init{

        if(!configFile.exists()){
            createConfigFile(configFile)
        }
        else
        {
            readConfigFile(configFile)
        }

    }

    private fun readConfigFile(configFile: File) {
        TODO("Not yet implemented")
    }

    private fun createConfigFile(configFile: File) {
        val writer=configFile.writer()


        val configString= "@projectsDir='$projectsDir'"

        writer.write(configString)

    }

    fun saveConfig(){

    }


    fun changeProjectsDir(file:File){
        projectsDir=file
    }


}