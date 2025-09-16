package com.example.maquetas.media


import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class Recorder(context: Context): MediaRecorder()
{
    fun start(outputFile: File){

        try
        {
            this.setAudioSource(MediaRecorder.AudioSource.MIC)
            this.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            this.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            this.setOutputFile(FileOutputStream(outputFile).fd)

            super.prepare()
            super.start()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun stop(){
        try {
            super.stop()
            reset()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }


}

class Player(var context:Context):MediaPlayer(){

    fun play(file:File){
        //create(context,file.toUri())
        try
        {
            setDataSource(FileInputStream(file).fd)
            prepare()
            start()
        }
        catch (e:Exception)
        {e.printStackTrace()}
    }


    override fun stop(){
        try{
            super.stop()
            release()
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }

}