package com.example.tasklist.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasklist.structure.StatusType
import com.example.tasklist.ui.components.DateBanner
import com.example.tasklist.ui.components.StatusIndicatorBar
import com.example.tasklist.ui.theme.TaskListTheme
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.res.painterResource
import com.example.tasklist.R
import com.example.tasklist.ui.components.StatusIndicator
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tasklist.structure.TaskNode
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import java.io.File
import org.json.JSONArray;
import org.json.JSONObject;

@Composable
fun HomeScreen (
    navController: NavController,
    modifier: Modifier = Modifier) {
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
            date = "Sat/8/02/2025",
            workDone = 0,
            workNotDone = 0,
            workOngoing = 0,
            modifier = Modifier.weight(1.2f)
        )
        TaskLists(
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
    modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxSize()
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
    modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(horizontal = 15.dp)
            .fillMaxSize()
    ) {
        // Container for navigation bar, horizontally placed
        Row(
            modifier = modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo
            Image(
                painter = painterResource(R.drawable.logo_icon),
                contentDescription = "logo image",
                modifier = Modifier.size(30.dp)
            )
            Spacer(Modifier.width(5.dp))
            Text (
                text = "TaskList",
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
                contentDescription = "add icon",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun TaskLists(modifier: Modifier = Modifier) {
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
                text = "Tasks",
                color = Color.White,
                fontSize = 20.sp
            )
            Text(
                text = "Total",
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
            val context = LocalContext.current // Get the app context

            val listOfTask = remember { mutableStateListOf<TaskNode>() } // Holds the list of tasks as state
            val taskJsonPath = File(context.filesDir,"task-list.json") // Get the file path of task-list.json

            // Checks if it exists
            if (!taskJsonPath.exists()) {
                taskJsonPath.writeText("[]")
            }

            val taskJsonArray =  JSONArray(taskJsonPath.readText()) // Place the content of task-list.json

            // Check if it contains a tasks
            if (taskJsonArray.length() > 0) {
                val taskLists = mutableListOf<TaskNode>() // Holds all the tasks it contains
                for (tasks in 0 until taskJsonArray.length()) { // Iterate every tasks it contains
                    val taskObject: JSONObject = taskJsonArray.getJSONObject(tasks) // Holds the task object
                    taskLists.add( // Add the task object to taskLists
                        TaskNode(
                            title = taskObject.getString("taskTitle"),
                            description = taskObject.getString("taskDescription"),
                            deadline = taskObject.getString("taskDeadline"),
                            status = StatusType.valueOf(taskObject.getString("taskStatus"))
                        )
                    )
                }
                listOfTask.addAll(taskLists) // Add them all at once to trigger single recomposition
            }

            // Load all the tasks
            if (listOfTask.size == 0) {
                Text(
                    text = "No ongoing tasks",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
            } else {
                for (tasks in listOfTask) {
                    TaskTab(
                        title = tasks.title,
                        status = tasks.status,
                        deadline = tasks.deadline,
                    )
                }
            }

        }
    }
}

@Composable
fun TaskTab(
    title: String,
    deadline: String,
    status: StatusType,
    modifier: Modifier = Modifier
) {
    Button (
        onClick = {/**/},
        colors = buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent
        ),
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
                    text = title,
                    color = Color.White
                )
                Text(
                    text = deadline,
                    color = Color.White
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
                    text = "Status:",
                    color = Color.White
                )

                StatusIndicator(
                    statusType = status
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