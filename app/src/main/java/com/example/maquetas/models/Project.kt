package com.example.maquetas.models

import android.content.Context
import com.example.maquetas.io.ConfigString
import com.example.maquetas.io.ProjectFileManager
import com.example.maquetas.models.ProjectObject
import java.io.File

//FILE PATH DEBE REFERENCIAR UN DIRECTORIO, NO UN ARCHIVO
class Project(val fileName:String="",val context:Context, val filePath: File=File(""),val projectName:String="New Project"): ProjectObject(filePath,fileName) {


    override lateinit var fileMan: ProjectFileManager
    var trackList=mutableListOf<Track>()
    var recordReady=true
    var isFav=false
    var projectCache:File?=null
    override var objectName=projectName

    constructor(context:Context):this(fileName = "", filePath = File(""),context=context, projectName = ""){
        /*
        if(context!=null)
        { projectCache = File("${context.cacheDir.toString()}/projects/$fileName") }

        fileMan= ProjectFileManager(this)
*/ }


    init{
        if(context!=null){
            projectCache=File("${context.cacheDir.toString()}/projects/$fileName")
            fileMan= ProjectFileManager(this)
        }
        else
        {
            val e=Exception()//TODO excepcion--context null, no pudo inicializarse la propiedad fileMan
            throw e
        }
    }




    fun addNewTrack(name:String){
        val trackPath= File("$projectCache/$name")
        val track=Track(trackName = name,filePath=trackPath)
        trackList.add(track)
        save()
    }

    fun record(selectedTrack:Int) {
        var track=trackList[selectedTrack]
        recordReady=false
        //TODO grabar la nueva toma y modificar la variable track
        track.recordNewTake()


        trackList[selectedTrack]=track

    }

    fun stopRecording(selectedTrack: Int){
        trackList[selectedTrack].stopRecording()
        recordReady=true
        save()
    }




    fun save(){
        fileMan.save()
    }


    fun saveToExternal(dir:File):Boolean{

        val saved=fileMan.saveToExternal(dir)


        //TODO Retornar si se guardo con exito, mostrar un cartel en la vista en tal caso
        return saved
    }

    fun getDataString(): ConfigString{
        val dataString=ConfigString()


        dataString.addKey("name",this)

        for(i in trackList.indices){
            dataString.addKey("track$i",trackList[i])
        }
        dataString.addKey("fav",isFav.toString())

        return dataString
    }

    fun play() {

        for(t in trackList){
            t.play()
        }
    }

}

