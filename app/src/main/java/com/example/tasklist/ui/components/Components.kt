package com.example.tasklist.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.tasklist.functions.toMonthName
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tasklist.structure.StatusType

@Composable
fun DateBanner(
    modifier: Modifier = Modifier,
    date: String
) {
    // Container banner, horizontally placed
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
        // day/mm/dd/yyyy
        // e.g. "Sat/8/2/2025" -> [Sat,8,2,2025]
        val extractDate = date.split("/")

        // Left Banner, vertically placed
        Column (
            modifier = modifier.weight(1f).fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = extractDate[0], // Day of the week e.g Mon
                color = Color.White,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,

            )
        }

        // Right Banner, vertically placed
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
    modifier: Modifier = Modifier
) {
    // Container for the status indicators, vertically placed
    Column (
        modifier = modifier.padding(horizontal = 20.dp).fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Tasks Done indicator
        StatusIndicator(
            status = workDone.toString(),
            statusType = StatusType.DONE
        )

        // Ongoing tasks indicator
        StatusIndicator(
            status = workOngoing.toString(),
            statusType = StatusType.ONGOING
        )

        // Missed Tasks indicator
        StatusIndicator(
            status = workNotDone.toString(),
            statusType = StatusType.MISSEDTASK
        )
    }
}

@Composable
fun StatusIndicator(
    statusType: StatusType,
    status: String = ""
) {
    // Container, horizontally placed
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Status value, empty by default - ""
        Text(
            text = status,
            color = Color.White
        )
        Spacer(Modifier.width(10.dp))
        // Status icon
        Image(
            painter = painterResource(statusType.icon),
            contentDescription = "status icon",
            modifier = Modifier.size(16.dp)
        )
        Spacer(Modifier.width(5.dp))
        // Status name e.g. Done
        Text(
            text = statusType.statusName,
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