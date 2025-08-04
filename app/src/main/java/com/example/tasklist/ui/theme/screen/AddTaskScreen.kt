package com.example.tasklist.ui.theme.screen

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
import com.example.tasklist.ui.theme.components.DateBanner
import com.example.tasklist.ui.theme.components.StatusIndicatorBar
import com.example.tasklist.ui.theme.TaskListTheme
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.example.tasklist.ui.theme.components.StatusIndicator
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue



@Composable
fun AddTaskScreen(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .statusBarsPadding()
            .background(Color(0xFF1E1E1E))
            .fillMaxSize()
    ) {
        val taskTitle by remember { mutableStateOf("") }
        val taskDescription by remember { mutableStateOf("") }
        val deadline by remember { mutableStateOf("") }
        val id by remember {mutableIntStateOf(0)}

        AddTaskNavBar(
            modifier = Modifier
                .weight(0.8f)
        )
        AddTaskBody(
            modifier = Modifier
                .weight(9.8f)
        )
    }
}

@Composable
fun AddTaskNavBar(modifier: Modifier = Modifier) {
    Row (
        modifier = modifier.fillMaxSize()
    ) {

    }
}

@Composable
fun AddTaskBody(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier.fillMaxSize()
    ) {

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Add task screen"
)
@Composable
fun AddTaskScreenPreview(modifier: Modifier = Modifier) {
    AddTaskScreen()
}