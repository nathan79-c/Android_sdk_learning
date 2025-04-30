package com.example.android_sdk_learning

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_sdk_learning.ui.theme.Android_sdk_learningTheme

class MainActivity : ComponentActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val validationResult = result.data?.getStringExtra("VALIDATION_RESULT")
                // Traitez le résultat de la deuxième activité ici
                // Par exemple, affichez un Toast ou mettez à jour l'UI
                println("Résultat de la validation : $validationResult")
            }
        }

        setContent {
            Android_sdk_learningTheme  {
                MainActivityScreen(onTasksSent = { tasks ->
                    val intent = Intent(this, SecondActivity::class.java)
                    intent.putExtra("TASKS_TO_VALIDATE", tasks)
                    resultLauncher.launch(intent)
                })
            }
        }
    }
}

@Composable
fun MainActivityScreen(onTasksSent: (String) -> Unit) {
    var tasks by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = tasks,
            onValueChange = { tasks = it },
            label = { Text("Entrez les tâches (séparées par des virgules)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onTasksSent(tasks) }) {
            Text("Envoyer les tâches pour validation")
        }
    }
}