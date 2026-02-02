package com.example.maquetas.models

import android.media.MediaMetadataRetriever
import android.util.Log
import com.example.maquetas.io.FileManager
import com.example.maquetas.io.TakeFileManager
import com.example.maquetas.io.TrackFileManager
import java.io.File

class Take(val takeName:String, val parentPath: File): ProjectObject(parentPath,takeName) {
    override val fileMan: TakeFileManager = TakeFileManager(this)
    val filePath=File("$parentPath/$takeName")
    override var objectName: String=takeName


    fun getDuration():Int{
        var duration=-1
        val mmr= MediaMetadataRetriever()
        mmr.setDataSource(filePath.path)
        val durationString=mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
        try {
            duration = durationString!!.toInt()
        }catch (e:Exception){
            e.printStackTrace()
        }
        return duration
    }


}