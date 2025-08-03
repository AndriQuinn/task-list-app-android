package com.example.tasklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasklist.ui.theme.TaskListTheme
import com.example.tasklist.ui.theme.screen.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskListTheme {

            }
        }
    }
}

@Composable
fun TaskListApp(modifier: Modifier = Modifier) {
    HomeScreen()
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Task List HomeScreen")
@Composable
fun GreetingPreview() {
    TaskListTheme {
        TaskListApp()
    }
}