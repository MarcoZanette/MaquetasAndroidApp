package com.example.maquetas

import android.os.Bundle
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
import com.example.maquetas.composables.Menu
import com.example.maquetas.models.Project
import com.example.maquetas.ui.theme.MaquetasTheme
import com.example.maquetas.views.MenuView
import com.example.maquetas.views.ProjectView
import kotlinx.serialization.Serializable
import java.io.File

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
                            MenuView().MainMenuView(
                                onCreateNewProject = {navController.navigate(ProjView)}
                            )
                        }

                                composable<ProjView> {
                                    ProjectView().MainProjectView(onNavigateUp = { navController.popBackStack() })
                                }
                                }
                    }
                }
                }
            }
        }

@Serializable
object MainMenu

@Serializable
object ProjView