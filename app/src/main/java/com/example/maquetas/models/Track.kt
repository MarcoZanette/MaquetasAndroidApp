package com.example.maquetas.models

import com.example.maquetas.io.FileManager
import com.example.maquetas.io.TrackFileManager
import java.io.File

class Track(val fileName:String,val trackName:String="", val filePath: File?=null): ProjectObject(filePath,fileName) {
    val trackFileMan= TrackFileManager(this)
    var takeList=trackFileMan.getTrackList()


    fun recordNewTake() {
        TODO("Not yet implemented")
    }

    override val fileMan: TrackFileManager= TrackFileManager(this)
}