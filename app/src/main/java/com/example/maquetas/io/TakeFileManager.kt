package com.example.maquetas.io

import com.example.maquetas.models.Take
import com.example.maquetas.models.Track
import java.io.File

class TakeFileManager(take: Take): FileManager() {
    var temp=take//TODO borrar

    override fun save(){

    }
    override fun load(): Take{
        return temp
    }

    override fun format(){

    }
}