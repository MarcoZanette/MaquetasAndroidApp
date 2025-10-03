package com.example.maquetas.models

import android.content.Context
import com.example.maquetas.io.ConfigString
import java.io.File

class Config(context: Context) {

    private val defaultProjectsDir:File=File(context.filesDir.toString() + "projects")

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
        val reader=configFile.reader()
        val configText=reader.readText()

        val configString= ConfigString(configText)
        val pDir=configString.search("projectsDir")!!


        projectsDir=File(pDir)

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