package com.example.maquetas.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import com.example.maquetas.models.Project
import com.example.maquetas.R
import com.example.maquetas.composables.MenuBar
import com.example.maquetas.composables.MenuItem
import com.example.maquetas.composables.ProjectCard

class MenuView {

    @Composable
    fun MainMenuView(onCreateNewProject:()->Unit){
        //Placeholders
        var pd= Project("Proyecto de ejemplo")
        //--------------
            Surface(color = colorResource(R.color.white)) {
                Column(modifier = Modifier.fillMaxSize()) {
                    MenuBar {
                        MenuItem(painter = painterResource(R.drawable.ic_launcher_foreground), onClick = {})
                        MenuItem(text="New"){onCreateNewProject}
                    }
                    ProjectCard(pd) { projectData ->
                        //navegar enviando la projectData como parametro
                    }
                }
            }
        }

    @Preview
    @Composable
    fun PvPreview(){
        MainMenuView(onCreateNewProject = {})
    }




}