package com.example.maquetas.io

import com.example.maquetas.models.Project

class ProjectFileManager(project: Project): FileManager() {

    var temp=project//TODO borrar

    override fun save(){

    }
    override fun load(): Project{
        return temp
    }

    override fun format(){

    }

}