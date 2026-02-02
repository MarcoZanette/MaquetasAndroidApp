package com.example.maquetas.models

import com.example.maquetas.io.ConfigString
import com.example.maquetas.io.FileManager
import com.example.maquetas.io.TrackFileManager
import com.example.maquetas.media.Player
import com.example.maquetas.media.Recorder
import java.io.File

//FILE PATH DEBE REFERENCIAR UN DIRECTORIO, NO UN ARCHIVO
class Track(val trackName:String="", val filePath: File): ProjectObject(filePath,trackName) {
    val activeTake=0

    //esta clase NO debe ocuparse de la persistencia de archivos, esto es trabajo del TrackFileManager
    var trackFileMan= TrackFileManager(this)
    var takeList=trackFileMan.getTakeList()
    var recorder= Recorder()
    var player: Player=Player()

    override var objectName=trackName

    constructor():this("",File(""))

    constructor(t:Track):this(t.trackName,t.filePath){
        trackFileMan=t.trackFileMan
        takeList=t.takeList
    }

    init{
        if(!filePath.exists())
        {
            filePath.mkdir()
        }
    }

    fun recordNewTake() {

        val takeName="take"+(takeList.size+1)

        val parentFile=trackFileMan.trackDir
        val newTake=Take(takeName,parentFile)

        recorder=Recorder()
        recorder.start(newTake.filePath)

    }

    fun stopRecording(){
        recorder.stop()
        recorder.release()
        val newTake=Take("take${(takeList.size+1)}",this.filePath)

        takeList.add(newTake)


    }


    fun save() {
        trackFileMan.save()
    }

    fun getDataString(): ConfigString {

        val configString=ConfigString()

        configString.addKey("name",this)

        for(i in takeList.indices){
            configString.addKey("take$i",takeList[i])
        }
        configString.addKey("activeTake",activeTake.toString())


        return configString

    }

    fun play(onCompletion:()->Unit){

        player=Player()
        player.play(takeList[activeTake].filePath)//TODO verificar funcionamiento de la lista de takes

        player.setOnCompletionListener {
            onCompletion()
        }
    }

    fun stop(){
        player.stop()
    }


    fun getDuration():Int{
        return takeList[activeTake].getDuration()
    }


    override val fileMan: TrackFileManager= TrackFileManager(this)
}