package com.example.tasklist.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tasklist.R
import com.example.tasklist.functions.markTaskDone
import com.example.tasklist.structure.StatusType
import com.example.tasklist.structure.TaskNode
import com.example.tasklist.ui.components.StatusIndicator

@Composable
fun TaskInfoScreen(
    taskNode: TaskNode,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Screen container, vertically placed
    Column (
        modifier = modifier
            .statusBarsPadding()
            .background(Color(0xFF1E1E1E))
            .fillMaxSize()
    ) {
        TaskInfoNavBar(
            modifier = Modifier.weight(0.8f),
            markDone = {
                markTaskDone(
                    id = taskNode.id.toInt(),
                    context = context
                )
            },
            backFunction = {navController.popBackStack()})
        TaskInfoBody(
            modifier = Modifier.weight(9.2f),
            taskNode = taskNode
        )
    }
}

@Composable
fun TaskInfoNavBar(
    markDone: () -> Unit,  // Mark done callback function
    backFunction: () -> Unit, // Back callback function
    modifier: Modifier = Modifier
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(horizontal = 15.dp)
//            .background(Color.Blue)
            .fillMaxSize()
    ) {
        // Back button
        Button (
            onClick = {backFunction()}, // Use back function
            colors = buttonColors(
                contentColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(5.dp),
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier.size(30.dp)
        ) {
            // Back icon
            Image (
                painter = painterResource(R.drawable.back_icon),
                contentDescription = "back icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(30.dp)
            )
        }
        // "Mark as done" button
        Button(
            onClick = {markDone()},
            colors = buttonColors(
                contentColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(0.dp),
        ) {
            // Back icon
            Text (
                text = "Done",
                color = Color.Green,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TaskInfoBody(
    taskNode: TaskNode,
    modifier: Modifier = Modifier
) {

    Column (
        modifier = modifier
            .padding(20.dp)
//            .background(Color.Green)
            .fillMaxSize()
    ) {
        // Header + Status
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize()
        ) {
            // Title and deadline
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = taskNode.title,
                    color = Color.White,
                    fontSize = 30.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(10.dp))
                Text (
                    text = "Until ${taskNode.deadline}",
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
            // Current status
            Column (
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)

            ) {
                StatusIndicator(
                    statusType = StatusType.valueOf(taskNode.status)
                )
            }
        }

        // Description container
        Column(
            modifier = Modifier
                .weight(1.5f)
                .padding(15.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Description",
                color = Color.White
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = taskNode.description,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Add task screen"
)
@Composable
fun TaskInfoScreenPreview(modifier: Modifier = Modifier) {
    TaskInfoScreen(
        taskNode = TaskNode(
            title = "Sample Task",
            description = "Idk what to write here",
            deadline = "1/1/2026",
            id = "1",
            status = "ONGOING"
        ),
        navController = rememberNavController())
}