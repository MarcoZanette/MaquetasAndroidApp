package com.example.maquetas.models

import android.content.Context
import com.example.maquetas.io.ConfigString
import com.example.maquetas.io.ProjectFileManager
import com.example.maquetas.models.ProjectObject
import java.io.File

//FILE PATH DEBE REFERENCIAR UN DIRECTORIO, NO UN ARCHIVO
class Project(val fileName:String="", val filePath: File,val projectName:String="New Project"): ProjectObject(filePath,fileName) {
    override var fileMan= ProjectFileManager(this)
    var trackList=mutableListOf<Track>()
    var recordReady=true
    var isFav=false

    override var objectName=projectName

    constructor(fileName:String,context: Context,projectName:String):this( //constructor para cuando no elijo un path especifico, generalmente si creo un nuevo proyecto sin seleccionar un path
        fileName = fileName,
        filePath = File("${context.cacheDir}/$projectName"),
        projectName = projectName
    ){
        objectName=projectName
    }

    constructor(p: Project):this(fileName = p.fileName, filePath = p.filePath, projectName = p.projectName){
        objectName=projectName
        trackList=p.trackList
        isFav=p.isFav
        fileMan=p.fileMan
    }

    constructor():this("",File(""),"")//Constructor vacio




    fun addNewTrack(name:String){
        val trackPath= File("$filePath/$name")
        val track=Track(trackName = name,filePath=trackPath)
        trackList.add(track)
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
    }

    fun save(){//TODO Retornar si se guardo con exito, mostrar un cartel en la vista en tal caso
        fileMan.save()
    }


    fun getDataString(): ConfigString{
        val dataString=ConfigString()


        dataString.addKey("project",this)

        for(i in trackList.indices){
            dataString.addKey("track$i",trackList[i])
        }
        dataString.addKey("fav",isFav.toString())

        return dataString
    }

}

