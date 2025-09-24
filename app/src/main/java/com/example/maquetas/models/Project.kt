package com.example.maquetas.models

import com.example.maquetas.io.ProjectFileManager
import com.example.maquetas.models.ProjectObject
import java.io.File

class Project(val fileName:String="", val filePath: File?=null,val projectName:String="New Project"): ProjectObject(filePath,fileName) {
    override val fileMan= ProjectFileManager(this)
    var trackList=mutableListOf<Track>()
    var recordReady=true
    var isFav=false



    fun addNewTrack(name:String){
        val track=Track(fileName=name, trackName = name)
        trackList.add(track)
    }

    fun record(selectedTrack:Int) {
        var track=trackList[selectedTrack]
        recordReady=false
        //TODO grabar la nueva toma y modificar la variable track
        track.recordNewTake()


        trackList[selectedTrack]=track

    }


}

