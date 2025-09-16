package com.example.maquetas.models

import com.example.maquetas.io.ProjectFileManager
import com.example.maquetas.models.ProjectObject
import java.io.File

class Project(val fileName:String="", val filePath: File?=null): ProjectObject(filePath,fileName) {
    override val fileMan= ProjectFileManager(this)
    var isFav=false



}

