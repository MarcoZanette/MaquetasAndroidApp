package com.example.maquetas.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.maquetas.models.Project
import com.example.maquetas.models.Track

class ProjectViewModel(project: Project): ViewModel() {
    var projectName=project.fileName
    var trackList=project.trackList!!.toMutableList()
    var selectedTrack:String=""
    
}



class ProjectViewmodelFactory(private val project:Project): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Project::class.java).newInstance(project)
    }

}