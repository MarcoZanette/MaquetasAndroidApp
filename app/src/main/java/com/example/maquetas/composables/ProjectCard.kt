package com.example.maquetas.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.maquetas.R
import com.example.maquetas.models.Project
import java.io.File


@Composable
fun ProjectCard(project: Project, cardOnclick:(project: Project)->Unit){ //al hacer clic en este componente debo llevar al usuario a la NoteView
    Surface(
        color = MaterialTheme.colorScheme.secondary,
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.outline),
        shape = RoundedCornerShape(25f),
        modifier = Modifier.padding(vertical = 1.dp, horizontal = 0.5.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .height(50.dp)
                .clickable { cardOnclick(project) }
        )
        {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.secondaryContainer,
                border = BorderStroke(0.5.dp, color = MaterialTheme.colorScheme.outline),
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(2.dp)
            ) {
                Box {
                    Text(
                        text = project.fileName.take(1).uppercase(),
                        modifier = Modifier
                            .fillMaxWidth(0.1f)
                            .align(alignment = Alignment.Center),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }

            }
            val iconSize=0.65f

            Row (modifier=Modifier.fillMaxSize()){
                Text(
                    text = project.fileName,
                    modifier = Modifier.fillMaxWidth(0.5f),
                    fontSize = 24.sp
                )
                IconButton(
                    onClick = { project.isFav = !project.isFav },
                    modifier = Modifier.fillMaxWidth(0.5f)
                ){ Icon(
                    modifier=Modifier.fillMaxSize(iconSize),
                    painter = painterResource(R.drawable.favorite),
                    contentDescription = stringResource(R.string.favorite)
                ) }
                IconButton(
                    onClick = {/*TODO*/ },
                    modifier = Modifier.fillMaxWidth()
                ){Icon(
                    modifier=Modifier.fillMaxSize(iconSize),
                    painter = painterResource(R.drawable.delete),
                    contentDescription = stringResource(R.string.delete)
                )}//TODO tendre que modificar la clase projectData para poder borrar el proyecto

            }
        }
    }

}


@Preview
@Composable
private fun pcPrev() {
    var pd=Project(fileName = "", filePath = File(""))
    ProjectCard(pd) { }
}