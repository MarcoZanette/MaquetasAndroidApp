package com.example.maquetas.models

import com.example.maquetas.io.FileManager
import java.io.File
//FILE PATH DEBE REFERENCIAR UN DIRECTORIO, NO UN ARCHIVO
abstract class ProjectObject(filePath: File?, fileName:String) {
    abstract val fileMan: FileManager
    abstract var objectName:String

}