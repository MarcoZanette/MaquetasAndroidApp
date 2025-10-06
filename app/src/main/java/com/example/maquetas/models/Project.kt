package com.example.maquetas.models

import android.content.Context
import com.example.maquetas.io.ProjectFileManager
import com.example.maquetas.models.ProjectObject
import java.io.File

class Project(val fileName:String="", val filePath: File,val projectName:String="New Project"): ProjectObject(filePath,fileName) {
    override val fileMan= ProjectFileManager(this)
    var trackList=mutableListOf<Track>()
    var recordReady=true
    var isFav=false

    constructor(fileName:String,context: Context,projectName:String):this( //constructor para cuando no elijo un path especifico, generalmente si creo un nuevo proyecto sin seleccionar un path
        fileName = fileName,
        filePath = File("${context.cacheDir}$projectName"),
        projectName = projectName
    )


    fun addNewTrack(name:String){
        val track=Track(fileName=name, trackName = name,filePath=filePath)
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


}

