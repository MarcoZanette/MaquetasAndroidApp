package com.example.maquetas.viewmodels

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.maquetas.models.Project
import com.example.maquetas.models.Track
import kotlinx.coroutines.flow.StateFlow

class ProjectViewModel(project: Project): ViewModel() {
    var projectName=project.fileName
    var trackList=mutableListOf<MutableState<Track>>()

    var selectedTrack:String=""
    var showPanel=mutableStateOf(false)
    var panelSize= mutableFloatStateOf(0.5f)
    var newTrackName= TextFieldState()

    init{
        for(track in project.trackList){
            trackList.add(mutableStateOf(track))
        }
    }


    fun addNewTrack(track: Track){
        trackList.add(mutableStateOf(track))
    }

}



class ProjectViewmodelFactory(private val project:Project): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Project::class.java).newInstance(project)
    }

}