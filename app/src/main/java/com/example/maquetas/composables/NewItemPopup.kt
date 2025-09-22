package com.example.maquetas.composables


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldDecorator
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.maquetas.R


@Composable
fun NewItemPopup(showDialog:Boolean=false,title:String,text:String,onDismiss:()->Unit,onConfirm:()->Unit,state: TextFieldState){

    if(showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            text = {
                Column {
                    Text(text)

                    BasicTextField(//TODO decorar o cambiar por TextField cuando salga la 1.4.0 de compose(Actualmente en alpha)
                        state=state
                    )

                }
            },
            title = {
                Text(title)
            },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(stringResource(R.string.confirmButton), color = MaterialTheme.colorScheme.onPrimary)
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(stringResource(R.string.dismissButton), color = MaterialTheme.colorScheme.onPrimary)
                }
            },
            containerColor = MaterialTheme.colorScheme.primary,
            textContentColor = MaterialTheme.colorScheme.onPrimary
        )
    }

}

@Preview
@Composable
private fun PopupPrev() {
    val state: TextFieldState= TextFieldState()//variable del viewmodel
    NewItemPopup(showDialog = true,text="Desea crear una nueva pista?",title="Atencion", onDismiss = {}, onConfirm = {},state=state)

}