package com.example.maquetas.composables


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.maquetas.R
import java.io.File

@Composable
fun ExportFilePopup (navigateTo:(File)->Unit,onConfirm:(file:File)-> Unit, onDismiss:()->Unit,showDialog: Boolean,dirList:List<File>){

    if(showDialog){

        var selectedDir:File?=null
        Dialog(
            onDismissRequest = {onDismiss()}) {


            Surface(color=MaterialTheme.colorScheme.surface) {
                Column() {
                    MenuBar() {
                        MenuItem() {
                            Icon(
                                painter = painterResource(R.drawable.arrow_back),
                                contentDescription = stringResource(R.string.arrow_back),
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Text(text = "Texto de ejemplo", color = MaterialTheme.colorScheme.onPrimary)

                    }
                    LazyColumn() {
                        //listado de subdirectorios actuales
                        items(items = dirList) { dir ->
                            Text(
                                text = dir.name,
                                modifier = Modifier.clickable(onClick = { navigateTo(dir) })
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

@Composable
fun ImportFilePopup(){

}

@Preview
@Composable
private fun exportPreview() {

    val f=File("/file")
    f.mkdir()

    val f2=File("/file/a")
    f2.mkdir()

    val l=listOf(f,f2)
    ExportFilePopup(onConfirm = {}, onDismiss = {},showDialog=true, dirList = l, navigateTo = {})

}