package com.example.maquetas.views

import android.graphics.Paint
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.tappableElementIgnoringVisibility
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Icon
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
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.maquetas.models.Project
import com.example.maquetas.R
import com.example.maquetas.composables.MenuBar
import com.example.maquetas.composables.MenuItem
import com.example.maquetas.viewmodels.ProjectViewModel
import com.example.maquetas.viewmodels.ProjectViewmodelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.maquetas.composables.NewItemPopup
import com.example.maquetas.composables.TrackCard
import com.example.maquetas.models.Track

class ProjectView {

    @Composable
    fun MainProjectView(project: Project,onNavigateUp:()->Unit){

        val viewmodel= viewModel<ProjectViewModel>(factory= ProjectViewmodelFactory(project = project))//Borre dependencias, puede no funcionar
        var showNewTrackPopup by remember { mutableStateOf(false) }

        NewItemPopup(
            title = stringResource(R.string.createNewTrackTitle),
            text = stringResource(R.string.createNewTrackText),
            showDialog = showNewTrackPopup,
            onDismiss = {
                showNewTrackPopup=false},
            onConfirm = {
                showNewTrackPopup=false
                project.addNewTrack(viewmodel.newTrackName.text.toString())
                viewmodel.addNewTrack(project.trackList[project.trackList.size-1])
                Log.println(Log.DEBUG,"ViewmodelInfo","ViewmodelTrackListSize: "+viewmodel.trackList.size)
                Log.println(Log.DEBUG,"ProjectInfo","ProjectTrackListSize: "+project.trackList.size)


                        },
            state = viewmodel.newTrackName
        )

        val iconModifier=Modifier
            .fillMaxSize()
            .aspectRatio(1f)
        Surface(color = colorResource(R.color.white)) {
            Column (modifier= Modifier.fillMaxSize()){
                MenuBar {
                    MenuItem { Icon(
                        painter = painterResource(R.drawable.arrow_back),
                        contentDescription = stringResource(R.string.arrow_back),
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier=iconModifier
                            .align(Alignment.Center)
                            .clickable(onClick = { onNavigateUp() })
                    ) }
                    MenuItem { Icon(
                        painter=painterResource(R.drawable.record),
                        contentDescription = stringResource(R.string.record),
                        tint=MaterialTheme.colorScheme.onSurface,
                        modifier=iconModifier
                            .align(Alignment.Center)
                            .clickable(onClick = {})
                    ) }
                    MenuItem{Icon(
                        painter=painterResource(R.drawable.import_file),
                        contentDescription = stringResource(R.string.import_file),
                        tint= MaterialTheme.colorScheme.onSurface,
                        modifier=iconModifier
                            .align(Alignment.Center)
                            .clickable(onClick = {})
                    )}
                    MenuItem{Icon(
                        painter=painterResource(R.drawable.export_file),
                        contentDescription = stringResource(R.string.export_file),
                        tint= MaterialTheme.colorScheme.onSurface,
                        modifier=iconModifier
                            .align(Alignment.Center)
                            .clickable(onClick = {})
                    )}
                    MenuItem{Icon(
                        painter =painterResource(R.drawable.play),
                        contentDescription = stringResource(R.string.play),
                        tint= MaterialTheme.colorScheme.onSurface,
                        modifier=iconModifier
                            .align(Alignment.Center)
                            .clickable(onClick = {})
                    )}
                    MenuItem{Icon(
                        painter=painterResource(R.drawable.add),
                        contentDescription = stringResource(R.string.addTrack),
                        tint= MaterialTheme.colorScheme.onSurface,
                        modifier=iconModifier
                            .align(Alignment.Center)
                            .clickable(onClick = {
                                //TODO popup donde el usuario escribira el nombre del nuevo track, por defecto Track+tracklistlength
                                val defaultName = project.trackList.size
                                showNewTrackPopup = true


                            })
                    )}


                }



                Column(modifier=Modifier.fillMaxSize()) {
                    LazyColumn(modifier =
                        Modifier
                            .animateContentSize()
                            .fillMaxSize(if (!viewmodel.showPanel.value) 0.95f else (1f - viewmodel.panelSize.floatValue))) {
                            items(viewmodel.trackList)
                            {
                                item->
                                TrackCard(track=item,modifier=Modifier.clickable(onClick = {viewmodel.selectedTrack=item.fileName}))
                            }

                    }

                    Row(modifier=Modifier.fillMaxSize()) {//LazyRow --- Colapsable
                        //fila de los controles de volumen
                        Surface (color= MaterialTheme.colorScheme.secondary){
                            Column {
                                TextButton(onClick = {viewmodel.showPanel.value=!viewmodel.showPanel.value})
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
        pd.trackList=mutableListOf<Track>(Track("asd"),Track("asd2"))
        MainProjectView(pd, onNavigateUp = {})
    }


}


