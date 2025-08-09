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
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.material3.ButtonDefaults.buttonColors
import com.example.tasklist.R
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.size
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import android.app.DatePickerDialog
import androidx.compose.ui.text.style.TextAlign
import java.util.Calendar
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tasklist.functions.addTaskFile
import com.example.tasklist.structure.StatusType
import com.example.tasklist.structure.TaskNode
import com.example.tasklist.ui.components.StatusIndicator

@Composable
fun TaskInfoScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    // Screen container, vertically placed
    Column (
        modifier = modifier
            .statusBarsPadding()
            .background(Color(0xFF1E1E1E))
            .fillMaxSize()
    ) {
        TaskInfoNavBar(
            modifier = Modifier.weight(0.8f),
            markDone = {},
            backFunction = {navController.popBackStack()})
        TaskInfoBody(
            modifier = Modifier.weight(9.2f),
            taskNode = TaskNode(
                title = "Try Task",
                description = "testing",
                status = StatusType.ONGOING,
                deadline = "Sat/8/9/2025"
            )
        )
    }
}

@Composable
fun TaskInfoNavBar(
    markDone: () -> Unit,
    backFunction: () -> Unit, // Back function callback
    modifier: Modifier = Modifier
) {
    // Container for navigation, horizontally placed
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(15.dp)
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
        // Mark as done button
        Button (
            onClick = {markDone()}, // Use back function
            colors = buttonColors(
                contentColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(5.dp),
            shape = RoundedCornerShape(0.dp),
        ) {
            Text(
                text = "Mark as done",
                color = Color.Green
            )
        }
    }
}

@Composable
fun TaskInfoBody(
    taskNode: TaskNode,
    modifier: Modifier = Modifier
) {
    // Header + Status
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
    ) {
        Column () {
    Text(
        text = taskNode.title,
        color = Color.White,
        fontSize = 25.sp
    )
    Spacer(Modifier.height(10.dp))
    Text(
        text = "Until ${taskNode.deadline}",
        color = Color.White,
        fontSize = 12.sp
    )
        }
        StatusIndicator(statusType= taskNode.status)
    }

    Column (
        modifier = modifier
            .padding(30.dp)
            .fillMaxSize()
    ) {
        
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Add task screen"
)
@Composable
fun TaskInfoScreenPreview(modifier: Modifier = Modifier) {
    TaskInfoScreen(navController = rememberNavController())
}