package com.example.tasklist.ui.screen

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tasklist.R
import com.example.tasklist.functions.getTotal
import com.example.tasklist.functions.removeFileTask
import com.example.tasklist.functions.toMonthName
import com.example.tasklist.functions.updatePastDeadlines
import com.example.tasklist.structure.StatusType
import com.example.tasklist.structure.TaskNode
import com.example.tasklist.ui.components.DateBanner
import com.example.tasklist.ui.components.StatusIndicator
import com.example.tasklist.ui.components.StatusIndicatorBar
import com.example.tasklist.ui.theme.TaskListTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.json.JSONArray
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen (
    navController: NavController,
    modifier: Modifier = Modifier
) {

    // Formatter - e.g. Sat/08/10/2025
    val formatter = SimpleDateFormat("E/MM/dd/yyyy", Locale.getDefault())
    // Gets current date using the declared formatter
    val currentDate = formatter.format(Date())

    val context = LocalContext.current // Get the app context
    val listOfTask = remember { mutableStateListOf<TaskNode>() } // Holds the list of tasks as state
    val taskJsonPath = File(context.filesDir,"task-list.json") // Get the file path of task-list.json

    // Update the file task if needed
    updatePastDeadlines(currentDate,context) // Update status if past deadline
    removeFileTask(currentDate,context) // Removes the file task if 1 month over the deadline

    // Place the content of task-list.json if it exists
    val taskJsonArray = if (taskJsonPath.exists()) {
        JSONArray(taskJsonPath.readText())
    } else { JSONArray() }

    // Check if it contains a tasks
    if (taskJsonArray.length() > 1) {
        val taskLists = mutableListOf<TaskNode>() // Holds all the tasks it contains
        for (tasks in 1 until taskJsonArray.length()) { // Iterate every tasks it contains
            taskLists.add( // Add the task object to taskLists
                Json.decodeFromString<TaskNode>(taskJsonArray.getString(tasks))
            )
        }
        listOfTask.addAll(taskLists) // Add them all at once to trigger single recomposition
    }

    Column (
        modifier = modifier
            .statusBarsPadding()
            .background(Color(0xFF1E1E1E))
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        NavBar(
            toAddScreen = { navController.navigate("addTaskScreen") },
            modifier = Modifier
                .background(Color.Transparent)
                .weight(0.8f)
        )
        TopBanner(
            date = currentDate,
            workDone = getTotal(listOfTask,"DONE"),
            workNotDone = getTotal(listOfTask,"MISSED"),
            workOngoing = getTotal(listOfTask,"ONGOING"),
            modifier = Modifier.weight(1.2f)
        )
        TaskLists(
            toTaskInfoScreen = {
                taskData ->
                    navController.navigate("askInfoScreen/$taskData")

            },
            listOfTask = listOfTask,
            modifier = Modifier
                .background(Color.Transparent)
                .weight(8f)
        )
    }
}

@Composable
fun TopBanner(
    date: String,
    workDone: Int,
    workNotDone: Int,
    workOngoing: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(vertical = 10.dp)
            .fillMaxSize()
    ) {
        DateBanner(
            date = date,
            modifier = Modifier.weight(1f)
        )
        StatusIndicatorBar(
            workDone = workDone,
            workNotDone = workNotDone,
            workOngoing = workOngoing,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun NavBar(
    toAddScreen: () -> Unit, // Function to go AddTaskScreen
    modifier: Modifier = Modifier
) {
    // Container for navigation bar, horizontally placed
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(horizontal = 15.dp)
            .fillMaxSize()
    ) {
        // Container for logo, horizontally placed
        Row(
            modifier = modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo
            Image(
                painter = painterResource(R.drawable.logo_icon),
                contentDescription = stringResource(R.string.logo_image_desc_txt),
                modifier = Modifier.size(30.dp)
            )
            Spacer(Modifier.width(5.dp))
            Text (
                text = stringResource(R.string.tasklist_header_txt),
                color = Color.White
            )
        }

        // Button to go to AddTaskScreen
        Button(
            onClick = {toAddScreen()},
            colors = buttonColors(
                contentColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(5.dp),
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier.size(30.dp)
        ) {
            // Add icon
            Image(
                painter = painterResource(R.drawable.add_icon),
                contentDescription = stringResource(R.string.add_icon_desc_txt),
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun TaskLists(
    toTaskInfoScreen: (String) -> Unit,
    listOfTask: MutableList<TaskNode>,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .padding(30.dp)
            .fillMaxSize()
    ) {
        // Header
        // Container for header, horizontally arranged
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.tasks_txt), // Header title
                color = Color.White,
                fontSize = 20.sp
            )
            Text(
                text = "${listOfTask.size}", // Shows number of tasks
                color = Color.White
            )
        }

        // Tasks list
        // Container for lists of tasks, vertically arranged
        Column (
            modifier = Modifier.padding(
                vertical = 30.dp,
                horizontal = 5.dp)
        ) {
            // Load all the tasks
            if (listOfTask.size == 0) { // If no tasks found
                Text(
                    text = stringResource(R.string.no_ongoing_tasks_txt),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
            } else {
                for (tasks in listOfTask) {
                    TaskTab(
                        taskNode = tasks, // Compose tasks
                        toTaskInfoScreen = { toTaskInfoScreen(it) }
                    )
                }
            }

        }
    }
}

@Composable
fun TaskTab(
    toTaskInfoScreen: (String) -> Unit,
    taskNode: TaskNode,
    modifier: Modifier = Modifier
) {
    Button (
        onClick = {
            val taskData = Uri.encode(Json.encodeToString(taskNode))
            toTaskInfoScreen(taskData)
                  }, // Function to go to TaskInfoScreen
        colors = buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent
        ),
        contentPadding = PaddingValues(5.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 15.dp)
                .fillMaxWidth()
        ) {

            // Left Part
            // title and deadline
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = modifier.weight(1f)
            ) {
                Text(
                    text = taskNode.title,
                    color = Color.White
                )
                Text(
                    text = "Until ${toMonthName(taskNode.deadline.split("/")[0])} ${
                        taskNode.deadline.split("/")[1]} ${
                        taskNode.deadline.split("/")[2]}",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }

            // Right Part
            // Status
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End,
                modifier = modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.status_txt),
                    color = Color.White
                )

                StatusIndicator(
                    statusType = StatusType.valueOf(taskNode.status)
                )
            }
        }
    }
    Spacer(Modifier.height(5.dp))
    HorizontalDivider(
        thickness = 1.dp,
        color = Color.White
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Homescreen"
)
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    TaskListTheme {
        HomeScreen(navController = rememberNavController())
    }
}