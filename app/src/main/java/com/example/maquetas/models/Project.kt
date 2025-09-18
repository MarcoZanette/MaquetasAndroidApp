package com.example.maquetas.models

import com.example.maquetas.io.ProjectFileManager
import com.example.maquetas.models.ProjectObject
import java.io.File

class Project(val fileName:String="", val filePath: File?=null,val projectName:String="New Project"): ProjectObject(filePath,fileName) {
    var trackList=mutableListOf<Track>()

    override val fileMan= ProjectFileManager(this)
    var isFav=false


    fun addNewTrack(name:String){
        val track=Track(fileName=name, trackName = name)
        trackList.add(track)
    }


}

