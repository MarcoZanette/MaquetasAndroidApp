package com.example.maquetas.io

import android.util.Log
import com.example.maquetas.models.Project
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
        return project
    }

    fun basicLoad(dir:File): Project?{

        val dataFile=File("$dir/${dir.name}.data")

        if(dataFile.exists()){

            val reader=dataFile.reader()
            val dataString=ConfigString(reader.readText())

            var name:String=""

            try
            {
                name = dataString.search("name")!!
            }catch (e:Exception){
                e.printStackTrace()
                Log.println(Log.DEBUG,"DataString",dataString.value)
            }

            val p= Project(name,dir,name)

            return p
        }
        else
        {
            return null
        }
    }

    fun loadInDir(dir:File){

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