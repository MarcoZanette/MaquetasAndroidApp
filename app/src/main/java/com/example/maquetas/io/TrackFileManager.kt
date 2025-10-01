package com.example.maquetas.io

import com.example.maquetas.models.Take
import com.example.maquetas.models.Track
import java.io.File

class TrackFileManager(track: Track): FileManager() {//esta clase se debe ocupar UNICAMENTE de la interaccion entre la clase Track y el sistema de archivos de android
    var temp=track//TODO borrar, solo sirgve para el return de la funcion load()
    lateinit var trackDir: File //cambiar en el constructor

    override fun save(){

    }
    override fun load(): Track{
        return temp
    }

    override fun format(){

    }

    fun getTrackList():MutableList<Take> {
        TODO("Not yet implemented")
    }
}