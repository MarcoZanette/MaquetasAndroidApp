package com.example.maquetas.models

import com.example.maquetas.io.FileManager
import java.io.File

abstract class ProjectObject(filePath: File?, fileName:String) {
    abstract val fileMan: FileManager

}