package com.example.tasklist.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasklist.TaskListApp
import com.example.tasklist.ui.theme.DateBanner
import com.example.tasklist.ui.theme.StatusIndicator
import com.example.tasklist.ui.theme.StatusIndicatorBar
import com.example.tasklist.ui.theme.TaskListTheme

@Composable
fun HomeScreen (modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .statusBarsPadding()
            .background(Color(0xFF1E1E1E))
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        NavBar(modifier = Modifier.background(Color.Transparent).weight(0.8f))
//        DateBanner(
//            date = "Sat/8/02/2025",
//            modifier = Modifier.background(Color.Blue).weight(1.2f)
//        )
        TopBanner(
            date = "Sat/8/02/2025",
            workDone = 0,
            workNotDone = 0,
            workOngoing = 0,
            modifier = Modifier.weight(1.2f))
        TaskLists(
            modifier = Modifier.background(Color.Transparent).weight(8f)
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
fun NavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxSize()
    ) {

    }
}

@Composable
fun TaskLists(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Tasks",
            color = Color.White
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Homescreen"
)
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    TaskListTheme {
        HomeScreen()
    }
}