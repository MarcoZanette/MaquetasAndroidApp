package com.example.maquetas.io

import com.example.maquetas.models.ProjectObject
import java.io.File

abstract class FileManager {
    val file: File?=null
    val rawData:String=""


    abstract fun save()
    abstract fun load(): ProjectObject
    abstract fun format()
}