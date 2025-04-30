package com.example.android_sdk_learning

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_sdk_learning.ui.theme.Android_sdk_learningTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tasks = intent.getStringExtra("TASKS_TO_VALIDATE") ?: ""
        setContent {
            Android_sdk_learningTheme {
                ValidationActivityScreen(tasks = tasks, onValidationResult = { isValid ->
                    val resultIntent = Intent()
                    resultIntent.putExtra("VALIDATION_RESULT", if (isValid) "Validé" else "Refusé")
                    setResult(RESULT_OK, resultIntent)
                    finish() // Termine cette activité et retourne le résultat
                })
            }
        }
    }
}

@Composable
fun ValidationActivityScreen(tasks: String, onValidationResult: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Tâches à valider :")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = tasks)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { onValidationResult(true) }) {
                Text("Valider")
            }
            Button(onClick = { onValidationResult(false) }) {
                Text("Refuser")
            }
        }
    }
}