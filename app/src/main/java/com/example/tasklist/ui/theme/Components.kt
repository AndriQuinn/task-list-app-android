package com.example.tasklist.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.tasklist.TaskListApp
import com.example.tasklist.functions.toMonthName
import com.example.tasklist.ui.theme.TaskListTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.tasklist.R


@Composable
fun DateBanner(
    modifier: Modifier = Modifier,
    date: String
) {
    Row (
        modifier = modifier
            .padding(
//                vertical = 10.dp,
                horizontal = 20.dp
            )
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // Format: day (week) e.g. Tue, month, day (month) e.g. 01, year
        // e.g. "Sat/8/2/2025" -> [Sat,8,2,2025]
        val extractDate = date.split("/")

        // Left Banner
        Column (
            modifier = modifier.weight(1f).fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = extractDate[0], // Day of the week
                color = Color.White,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,

            )
        }

        // Right Banner
        Column(
            modifier = modifier.weight(1f).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Month, day e.g. Aug 03
            Row() {
                Text(
                    text = "${toMonthName(extractDate[1])} ${extractDate[2]}",
                    color = Color.White,
                )
            }
            // Year
            Text(
                text = extractDate[3],
                color = Color.White,
            )
        }
    }
}

@Composable
fun StatusIndicatorBar(
    workDone: Int,
    workNotDone: Int,
    workOngoing: Int,
    modifier: Modifier = Modifier) {
    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {


        // Tasks Done
        StatusIndicator(
            status = workDone,
            icon = R.drawable.done,
            name = "Done"
        )

        // Ongoin tasks
        StatusIndicator(
            status = workNotDone,
            icon = R.drawable.ongoing,
            name = "Ongoing"
        )

        // Missed Tasks
        StatusIndicator(
            status = workDone,
            icon = R.drawable.not_done,
            name = "Missed task"
        )
    }
}

@Composable
fun StatusIndicator(
    icon: Int,
    name: String,
    status: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$status",
            color = Color.White
        )
        Spacer(Modifier.width(10.dp))
        Image(
            painter = painterResource(icon),
            contentDescription = "status icon",
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = name,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun StatusIndicatorPreview(modifier: Modifier = Modifier) {
    StatusIndicatorBar(
        workOngoing = 0,
        workDone = 0,
        workNotDone = 0
    )
}