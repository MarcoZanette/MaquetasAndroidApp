package com.example.maquetas.io

import com.example.maquetas.models.Track
import java.io.File

class TrackFileManager(track: Track): FileManager() {
    var temp=track//TODO borrar

    override fun save(){

    }
    override fun load(): Track{
        return temp
    }

    override fun format(){

    }
}