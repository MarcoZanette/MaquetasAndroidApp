package com.example.maquetas.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.maquetas.models.Track
import java.io.File


@Composable
fun TrackCard(track: Track,modifier:Modifier=Modifier,isSelected:Boolean=false){


    var boxModifier=modifier.border(width = 2.dp, shape = RectangleShape, color = MaterialTheme.colorScheme.outline)

    if(isSelected){
        boxModifier=modifier.border(width = 2.dp, shape = RectangleShape, color = MaterialTheme.colorScheme.outlineVariant)

    }

    Box(
        modifier = boxModifier,
    ) {

        Surface (color = MaterialTheme.colorScheme.secondary) {
            Column(modifier=Modifier.padding(2.dp)) {
                Text(track.fileName)

                Text(
                    "Aca van las ondas de sonido flaco",
                    modifier = Modifier.border(
                        width = 1.dp,
                        shape = RectangleShape,//modificar este borde para que solo este arriba
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                )
            }
        }
    }


}


@Preview
@Composable
private fun TCPrev() {
    val t= Track("Track1","asd", File(""))

    TrackCard(
        track = t,
        modifier = Modifier,
        isSelected = false
    )

}