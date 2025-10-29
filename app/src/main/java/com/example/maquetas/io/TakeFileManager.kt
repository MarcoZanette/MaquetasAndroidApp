package com.example.maquetas.io

import com.example.maquetas.models.ProjectObject
import com.example.maquetas.models.Take
import com.example.maquetas.models.Track
import java.io.File

class TakeFileManager(take: Take): FileManager() {
    var temp=take//TODO borrar

    override fun save(){

    }
    override fun load(dir:File): Take{
        return temp
    }

    override fun readDataFile(dir: File): Take? {
        TODO("Not yet implemented")
    }
    private fun format(): String {
return ""
    }
}