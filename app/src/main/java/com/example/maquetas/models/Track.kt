package com.example.maquetas.models

import com.example.maquetas.io.FileManager
import com.example.maquetas.io.TrackFileManager
import com.example.maquetas.media.Recorder
import java.io.File

//FILE PATH DEBE REFERENCIAR UN DIRECTORIO, NO UN ARCHIVO
class Track(val trackName:String="", val filePath: File): ProjectObject(filePath,trackName) {//esta clase NO debe ocuparse de la persistencia de archivos, esto es trabajo del TrackFileManager
    val trackFileMan= TrackFileManager(this)
    var takeList=trackFileMan.getTrackList()
    var recorder= Recorder()

    override var objectName=trackName

    init{
        if(!filePath.exists())
        {
            filePath.mkdir()
        }
    }

    fun recordNewTake() {

        val takeName="take "+(takeList.size+1)

        val parentFile=trackFileMan.trackDir
        var newTake=Take(takeName,parentFile)

        recorder=Recorder()
        recorder.start(newTake.filePath)

    }

    fun stopRecording(){
        recorder.stop()
        recorder.release()
        addTake("take "+(takeList.size+1))
    }

    private fun addTake(name: String) {
        val newTake=Take(name,this.filePath)

        takeList.add(newTake)
        trackFileMan.addTake(newTake)
    }

    fun save() {
        trackFileMan.save()
    }

    override val fileMan: TrackFileManager= TrackFileManager(this)
}