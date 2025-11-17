package com.example.maquetas.io

import android.content.Context
import android.util.Log
import com.example.maquetas.models.Project
import com.example.maquetas.models.ProjectObject
import com.example.maquetas.models.Track
import kotlinx.serialization.StringFormat
import java.io.File
import java.io.FileWriter
import java.io.OutputStreamWriter
import java.net.ProtocolException

class ProjectFileManager(private val project: Project): FileManager() {

    constructor(context: Context):this(project = Project("",context))


    init{
        if(project.context!=null){
            save()
        }
    }

    fun saveToExternal(dir:File){
        //TODO -- Agregar parametros a save y usarla para esta misma funcion?
    }


    override fun save() {

        if(project.projectCache!=null) {
            this.save(dir = project.projectCache!!)

            Log.println(Log.DEBUG,"ProjectCacheInfo","ProjectCache: ${project.projectCache}")

        }else
        {
            Log.println(Log.DEBUG,"NullProjectCache","ProjectCache${project.projectCache}")
            val e=Exception()//TODO Crear esta exception - project Cache is null
            throw e
        }
    }

    override fun load(dir: File): Project {
        TODO("Not yet implemented")
    }

    fun save(dir:File){


        if(!dir.exists()){


            val a=dir.mkdirs()


            if (!a){
                throw Exception() //TODO crear excepcion para cuando no se puede crear el cache de directorios/cache de este proyecto particular
            }
        }


        val dataString=project.getDataString()
        val path=dir
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


    fun load(dir:File,context: Context): Project{//TODO Cargar proyecto

        var p=Project(context)
        try{
            p= readDataFile(dir,context)!!
        }
        catch (e:Exception){
            e.printStackTrace()
        }

        //leer tracks

        val fileMan= TrackFileManager()
        for(i in p.trackList.indices){
            if (p.trackList[i].filePath.exists()){
                p.trackList[i]=fileMan.load(p.trackList[i].filePath)
            }
        }

        return p
    }

    override fun readDataFile(dir:File,context:Context): Project?{

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

                    val trackName=dataString.search(t)!!
                    val track=Track(trackName = trackName, filePath = File("$dir/$trackName"))

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

            val p= Project(fileName=name, filePath = dir,context=context,projectName=name)
            p.trackList=trackList
            p.isFav=isFav

            return p
        }
        else
        {
            return null
        }
    }




}