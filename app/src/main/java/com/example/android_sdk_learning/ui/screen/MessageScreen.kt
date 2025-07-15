package com.example.android_sdk_learning.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FieldMessage(onClick: () -> Unit,modifier: Modifier){
    Column(modifier = modifier)  {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            value = text,
            label = {Text(text = "Message")},
            onValueChange = {
                text = it
            }
        )
        Button(onClick = onClick) {
            Text(text = "Send")
        }
    }
}


@Preview
@Composable
fun FieldMessagePreview(){
    FieldMessage(onClick = {}, modifier = Modifier)
}
