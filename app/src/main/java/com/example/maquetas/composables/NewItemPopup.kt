package com.example.maquetas.composables

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog


@Composable
fun NewItemPopup(){
    Dialog(
        onDismissRequest = TODO(),
        properties = TODO()
    ) {
        Card { Text("asd") }
    }
}


@Preview
@Composable
private fun PopupPrev() {
    NewItemPopup()
}