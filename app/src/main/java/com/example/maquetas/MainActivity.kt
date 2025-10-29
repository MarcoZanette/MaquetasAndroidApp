package com.example.maquetas

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.maquetas.composables.Menu
import com.example.maquetas.io.ProjectFileManager
import com.example.maquetas.models.Project
import com.example.maquetas.ui.theme.MaquetasTheme
import com.example.maquetas.views.MenuView
import com.example.maquetas.views.ProjectView
import kotlinx.serialization.Serializable
import java.io.File
import java.security.Permission

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {

            MaquetasTheme {
                        Box(modifier = Modifier.windowInsetsPadding(insets = WindowInsets.statusBars)) {

                            val navController: NavHostController = rememberNavController()

                            NavHost(navController = navController, startDestination = MainMenu){

                                composable<MainMenu>{
                                    val projectList=getCacheList()
                            MenuView(applicationContext).MainMenuView(
                                onCreateNewProject = {p ->
                                    navController.navigate(NewProjView(p))},
                                projectList = projectList,
                                onProjectLoad = {
                                    p->navController.navigate(
                                        PView(p.filePath.toString(),p.projectName)
                                    )
                                }
                            )
                        }

                                composable<NewProjView> {
                                    val args=it.toRoute<NewProjView>()
                                    ProjectView(context =applicationContext ).
                                    MainProjectView(
                                        onNavigateUp = { navController.popBackStack() },
                                        requestPermission={permission:String ->
                                            requestPermissions(arrayOf(permission),1)
                                        },
                                        project= Project(context =applicationContext, projectName = args.projectName, fileName =args.projectName)

                                    )
                                }

                                composable <PView>{
                                    val args=it.toRoute<PView>()
                                    //cargar el proyecto
                                    var fileMan= ProjectFileManager()//TODO en la clase ProjectFileMan
                                    var project= fileMan.load(File(args.projectDir))

                                    ProjectView(context=applicationContext).
                                    MainProjectView(
                                        onNavigateUp = { navController.popBackStack() },
                                        requestPermission={permission:String ->
                                            requestPermissions(arrayOf(permission),1)
                                        },
                                        project=project

                                    )

                                }




                                }
                    }
                }
                }
            }
    fun getCacheList():List<Project>{

        val dirList=cacheDir.list()
        val projectList=mutableListOf<Project>()

        if(dirList!!.size!=0){
            for(dir in dirList){

                val fm= ProjectFileManager()
                val currentDir=File("$cacheDir/$dir")

                val p:Project?=fm.basicLoad(currentDir)

                if(p!=null) {
                    projectList.add(p)
                }
                else{
                    Log.println(Log.DEBUG,"Project load info","$dir, $currentDir")

                }
            }
        }
        return projectList
    }
        }

@Serializable
object MainMenu

@Serializable
data class NewProjView(val projectName:String)

@Serializable
data class PView(val projectDir:String,val projectName:String)