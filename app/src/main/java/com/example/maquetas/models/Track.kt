package com.example.maquetas.models

import com.example.maquetas.io.FileManager
import com.example.maquetas.io.TrackFileManager
import com.example.maquetas.media.Recorder
import java.io.File

class Track(val fileName:String,val trackName:String="", val filePath: File?=null): ProjectObject(filePath,fileName) {//esta clase NO debe ocuparse de la persistencia de archivos, esto es trabajo del TrackFileManager
    val trackFileMan= TrackFileManager(this)
    var takeList=trackFileMan.getTrackList()
    var recorder= Recorder()

    fun recordNewTake() {

        val takeName="take "+(takeList.size+1)

        val outputFile=trackFileMan.trackDir
        var newTake=Take(takeName,outputFile)

        recorder.start(newTake.filePath)//TODO Cambiar a cachedir para que no persistan todas las grabaciones

    }

    fun stopRecording(){
        recorder.stop()
    }

    override val fileMan: TrackFileManager= TrackFileManager(this)
}