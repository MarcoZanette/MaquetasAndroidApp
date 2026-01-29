package com.example.maquetas.views

import android.content.Context
import android.os.Environment
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.maquetas.models.Project
import com.example.maquetas.R
import com.example.maquetas.composables.SearchFilePopup
import com.example.maquetas.composables.MenuBar
import com.example.maquetas.composables.MenuItem
import com.example.maquetas.composables.NewItemPopup
import com.example.maquetas.composables.ProjectCard
import com.example.maquetas.io.ProjectFileManager

class MenuView(val context: Context) {


    @Composable
    fun MainMenuView(onProjectLoad:(project:Project)->Unit,onCreateNewProject:(name:String)->Unit,projectList:List<Project>){
        //Placeholders
        var pd= Project(fileName = "asd",projectName = "Proyecto de ejemplo",context=context)
        //--------------
        var showNewProjectPopUp= remember{mutableStateOf(false)}
        var showLoadProjectPopUp=remember{mutableStateOf(false)}
        var currentExternalDir=remember{mutableStateOf(Environment.getExternalStorageDirectory())}

        Surface(color = colorResource(R.color.white)) {
                var newItemState= remember{ TextFieldState() }
                NewItemPopup(
                    showDialog=showNewProjectPopUp.value,
                    title="Crear Nuevo Proyecto?",
                    text="Desea crear un nuevo proyecto?",
                    onDismiss ={},
                    onConfirm = {onCreateNewProject(newItemState.text.toString())},
                    state = newItemState
                )

            SearchFilePopup(navigateTo = { d->currentExternalDir.value=d},
                onConfirm = {
                    d->
                    val fm= ProjectFileManager(context)
                    val p =fm.load(d,context)
                    onProjectLoad(p)
                },
                onDismiss = {
                    showLoadProjectPopUp.value=false
                },
                showDialog = showLoadProjectPopUp.value,
                dir=currentExternalDir.value)


                Column(modifier = Modifier.fillMaxSize()) {
                    MenuBar {
                        MenuItem(painter = painterResource(R.drawable.ic_launcher_foreground), onClick = {})
                        MenuItem{
                            Text(text="New",modifier=Modifier.clickable(onClick= {
                                showNewProjectPopUp.value = true
                            })) //(onClick = onCreateNewProject))
                        }
                        MenuItem{
                            Text(text="Load",modifier=Modifier.clickable(onClick = {
                                showLoadProjectPopUp.value=true
                            }))
                        }
                    }

                    for(i in projectList.indices){
                        ProjectCard(projectList[i]) { p ->
                            onProjectLoad(p)//TODO mostrar popup de confirmacion
                        }
                    }
                }
            }
        }





}