package com.example.maquetas.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.maquetas.models.Project
import com.example.maquetas.R
import com.example.maquetas.composables.MenuBar
import com.example.maquetas.composables.MenuItem

class ProjectView {

    @Composable
    fun MainProjectView(project: Project,onNavigateUp:()->Unit){
        var panelIsHidden by remember { mutableStateOf(true) }
        var panelSize by remember{ mutableFloatStateOf(0.5f) }

        Surface(color = colorResource(R.color.white)) {
            Column (modifier= Modifier.fillMaxSize()){
                MenuBar {
                    MenuItem(painter = painterResource(R.drawable.ic_launcher_foreground)) {
                        //onclick
                        onNavigateUp()
                    }
                }




                Column(modifier=Modifier.fillMaxSize()) {
                    LazyColumn(modifier =
                        Modifier.animateContentSize()
                        .fillMaxSize(if (panelIsHidden) 0.95f else (1f - panelSize))) {
                            //items(){
                    //columna de las pistas
                    // }

                    }

                    Row(modifier=Modifier.fillMaxSize()) {//LazyRow --- Colapsable
                        //fila de los controles de volumen
                        Surface (color= MaterialTheme.colorScheme.secondary){
                            Column {
                                TextButton(onClick = {panelIsHidden=!panelIsHidden})
                                    {Text(text="Hide/Show",color=MaterialTheme.colorScheme.onSecondary)}

                                Surface(
                                    color = MaterialTheme.colorScheme.secondaryContainer,
                                    modifier = Modifier
                                        .fillMaxSize()
                                ){
                                        
                                }
                            }
                        }
                    }
                }


            }
        }
    }


    @Composable
    fun MainProjectView(onNavigateUp:()->Unit){
        var newProject= Project()
        MainProjectView(project = newProject,onNavigateUp=onNavigateUp)
    }



    @Preview
    @Composable
    fun PvPreview(){
        var pd= Project("Example")
        MainProjectView(pd, onNavigateUp = {})
    }



}