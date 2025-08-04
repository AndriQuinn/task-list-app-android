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
import androidx.compose.ui.unit.dp
import com.example.tasklist.ui.theme.components.StatusIndicator


@Composable
fun HomeScreen (modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .statusBarsPadding()
            .background(Color(0xFF1E1E1E))
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        NavBar(modifier = Modifier
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
fun NavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxSize()
    ) {

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
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tasks",
                color = Color.White,
                fontSize = 30.sp
            )
            Text(
                text = "Total",
                color = Color.White
            )
        }

        // Tasks list
        Column (
            modifier = Modifier.padding(
                vertical = 30.dp,
                horizontal = 5.dp)
        ) {
            TaskTab(
                title = "Project TaskList",
                deadline = "8/8/16",
                status = StatusType.DONE
            )
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
    Row (
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
            Text (
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
        HomeScreen()
    }
}