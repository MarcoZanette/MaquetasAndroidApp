package com.example.maquetas.composables


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.maquetas.R
import java.io.File

@Composable
fun SearchFilePopup (navigateTo:(File)->Unit, onConfirm: (file:File)-> Unit, onDismiss:()->Unit, showDialog: Boolean, dir:File){

    if(showDialog){

        var selectedDir:File?= dir
        Dialog(
            onDismissRequest = {onDismiss()}) {


            Surface(color=MaterialTheme.colorScheme.surface) {
                Column() {
                    MenuBar() {
                        MenuItem() {
                            Icon(
                                painter = painterResource(R.drawable.arrow_back),
                                contentDescription = stringResource(R.string.arrow_back),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable(onClick = {
                                        if (dir.parentFile != null) {
                                            navigateTo(dir.parentFile!!)
                                        } else {
                                            Log.println(
                                                Log.INFO,
                                                "Dir nav error",
                                                "No se puede encontrar el directorio padre."
                                            )
                                        }

                                    })
                            )
                        }

                        Text(text = "Texto de ejemplo", color = MaterialTheme.colorScheme.onPrimary)

                    }
                    LazyColumn() {
                        //listado de subdirectorios actuales
                        items(items = dir.listFiles()!!.toList()) { d -> //TODO error al navegar a directorios vacios o que no tienen permisos (La app no deberia permitir al usuario entrar en ellos)
                            Text(
                                text = d.name,
                                modifier = Modifier.clickable(onClick = {
                                    selectedDir=d
                                    if(d.isDirectory)
                                        {
                                        navigateTo(d)
                                    }
                                    }
                                )
                            )
                        }
                    }
                    TextButton(onClick = {
                        if (selectedDir != null) {
                            onConfirm(selectedDir)
                        } else {
                            //TODO notificar que no se ha seleccionado ningun directorio
                            Log.println(
                                Log.WARN,
                                "No se ha guardado el archivo",
                                "No se ha seleccionado ningun directorio, por lo tanto el archivo no puede ser exportado"
                            )
                        }
                    }
                    ) {
                        Text("Guardar")
                    }

                }
            }

        }

    }
}


@Preview
@Composable
private fun searchPreview() {

    val f=File("/file")


    val f2=File("/file/a")


    SearchFilePopup(onConfirm = {}, onDismiss = {},showDialog=true, dir = f, navigateTo = {})

}