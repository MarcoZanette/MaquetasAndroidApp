package com.example.maquetas.composables

import android.view.MenuItem
import android.view.Surface
import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.maquetas.R
import com.example.maquetas.ui.theme.MaquetasTheme


@Composable
fun MenuBar(content:@Composable RowScope.()->Unit) {//cambiar content por una lista de menuitems

    Surface(color= MaterialTheme.colorScheme.primary) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(48.dp),
            horizontalArrangement = Arrangement.Start
        )
        {
            content()
        }
    }

}

@Composable
fun RowScope.MenuItem(modifier:Modifier=Modifier, content:@Composable BoxScope.()->Unit){
    Box(modifier=modifier
        .fillMaxHeight(1f)
        .aspectRatio(1f))
    {
        content()
    }
}


@Composable
fun RowScope.MenuItem(modifier:Modifier=Modifier,painter: Painter,onClick:()->Unit){//recibe una funcion como parametro


        IconButton(
            onClick = onClick,
            modifier = modifier
                .fillMaxHeight(1f)
                .aspectRatio(ratio = 1f)
        ) {
            Icon(painter = painter, contentDescription = "")
        }
    }

@Composable
fun RowScope.MenuItem(modifier:Modifier=Modifier,text:String,onClick:()->Unit){//recibe una funcion como parametro


    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxHeight(1f)
            .aspectRatio(ratio = 1f)
    ) {
        Text(text=text, color =MaterialTheme.colorScheme.surface)
    }
}



@Preview
@Composable
fun Menu(){

    MaquetasTheme {
        Column {
            MenuBar {
                MenuItem {
                    Icon(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = ""
                    )
                }

                MenuItem(text="text",modifier=Modifier){}
            }

        }
    }
}