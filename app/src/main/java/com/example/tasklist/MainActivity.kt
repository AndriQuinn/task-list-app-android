package com.example.tasklist

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tasklist.structure.TaskNode
import com.example.tasklist.ui.screen.AddTaskScreen
import com.example.tasklist.ui.screen.HomeScreen
import com.example.tasklist.ui.screen.TaskInfoScreen
import com.example.tasklist.ui.theme.TaskListTheme
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskListTheme {
                TaskListApp()
            }
        }
    }
}

@Composable
fun TaskListApp(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()

    // Navigation address
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController = navController) }
        composable("addTaskScreen") { AddTaskScreen(navController = navController) }
        composable(
            route = "taskInfoScreen/{taskData}",
            arguments = listOf(navArgument("taskData") { type = NavType.StringType })
        ) { backStackEntry ->
            val taskUri = backStackEntry.arguments?.getString("taskData")
            val taskUriDecoded = Uri.decode(taskUri.toString())
            val taskData = Json.decodeFromString<TaskNode>(taskUriDecoded)

            TaskInfoScreen(
                taskNode = taskData,
                navController = navController
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Task List HomeScreen"
)
@Composable
fun HomeScreenPreview() {
    TaskListTheme {
        HomeScreen(navController = rememberNavController())
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Task List HomeScreen")
@Composable
fun AddTaskScreenPreview() {
    TaskListTheme {
        AddTaskScreen(navController = rememberNavController())
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Task List HomeScreen")
@Composable
fun TaskInfoScreenPreview() {
    TaskListTheme {
        TaskInfoScreen(
            taskNode = TaskNode(
                title = "Sample Task",
                description = "Idk what to write here",
                deadline = "1/1/2026",
                id = "1",
                status = "ONGOING"
            ),
            navController = rememberNavController()
        )
    }
}