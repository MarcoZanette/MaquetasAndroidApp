package com.example.maquetas.composables

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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


@Composable
fun TrackCard(track: Track,cardOnClick:()->Unit){

    Box(
        modifier = Modifier.border(width = 2.dp, shape = RectangleShape, color = MaterialTheme.colorScheme.secondary),
    ) {

        Surface (color = MaterialTheme.colorScheme.surface) {
            Column(modifier=Modifier.padding(2.dp)) {
                Text(track.fileName)

                Text(
                    "Aca van las ondas de sonido flaco",
                    modifier = Modifier.border(
                        width = 1.dp,
                        shape = RectangleShape,//modificar este borde para que solo este arriba TODO
                        color = MaterialTheme.colorScheme.primary
                    )
                )/*TODO*/
            }
        }
    }

}


@Preview
@Composable
private fun TCPrev() {
    val t= Track("Track1")

    TrackCard(t,{})

}