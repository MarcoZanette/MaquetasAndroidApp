package com.example.maquetas.viewmodels

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.maquetas.models.Project
import com.example.maquetas.models.Track
import kotlinx.coroutines.flow.StateFlow

class ProjectViewModel(project: Project): ViewModel() {
    var projectName=project.fileName
    var trackList=project.trackList.toMutableStateList()//Solamente notifica si se añaden o se quitan items en la lista, para actualizar los elementos en si deberia borrar y volver a añadir el mismo elemento

    var selectedTrack:String=""
    var showPanel=mutableStateOf(false)
    var panelSize= mutableFloatStateOf(0.5f)
    var newTrackName= TextFieldState()



    fun addNewTrack(track: Track){
        trackList.add(track)
    }

}



class ProjectViewmodelFactory(private val project:Project): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Project::class.java).newInstance(project)
    }

}