package com.example.maquetas.models

import com.example.maquetas.io.FileManager
import com.example.maquetas.io.TrackFileManager
import java.io.File

class Track(val fileName:String, val filePath: File?=null): ProjectObject(filePath,fileName) {
    override val fileMan: TrackFileManager= TrackFileManager(this)
}