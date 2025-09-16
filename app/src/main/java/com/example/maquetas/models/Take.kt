package com.example.maquetas.models

import com.example.maquetas.io.FileManager
import com.example.maquetas.io.TakeFileManager
import com.example.maquetas.io.TrackFileManager
import java.io.File

class Take(val fileName:String, val filePath: File?=null): ProjectObject(filePath,fileName) {
    override val fileMan: TakeFileManager = TakeFileManager(this)
}