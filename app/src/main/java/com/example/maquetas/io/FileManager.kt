package com.example.maquetas.io

import android.content.Context
import com.example.maquetas.models.ProjectObject
import java.io.File

abstract class FileManager {
    val file: File?=null


    abstract fun save()
    abstract fun load(dir:File): ProjectObject
    abstract fun readDataFile(dir:File,context: Context): ProjectObject?
}