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
import androidx.compose.material3.Button
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
import androidx.compose.foundation.Image
import androidx.compose.material3.ButtonDefaults.buttonColors
import com.example.tasklist.R
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.size
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.*
import android.app.DatePickerDialog
import java.util.Calendar


@Composable
fun AddTaskScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // Hold selected date as a state
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var selectedDate by remember { mutableStateOf("") }

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, y, m, d ->
                selectedDate = "$d/${m + 1}/$y"
            },
            year,
            month,
            day
        )
    }


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
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 15.dp)
            .fillMaxSize()
    ) {
        Button (
            onClick = {/**/},
            colors = buttonColors(
                contentColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(5.dp),
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier.size(30.dp)
        ) {
            Image (
                painter = painterResource(R.drawable.back_icon),
                contentDescription = "back icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(30.dp)
            )
        }
        Button (
            onClick = {/**/},
            colors = buttonColors(
                contentColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(5.dp),
            shape = RoundedCornerShape(0.dp)
        ) {
            Text (
                text = "Add",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun AddTaskBody(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier.fillMaxSize()
    ) {
        // set title field
        TextField(
            value = "",
            onValueChange = {},
            label = {Text("Title")},
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White
            ),
            modifier = modifier.fillMaxWidth()
        )

        // set deadline field
        TextField(
            value = "",
            onValueChange = {},
            label = {Text("Deadline")}
        )

        // set description field
        TextField(
            value = "",
            onValueChange = {},
            label = {Text("Description")},
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,

            ),
            modifier = modifier.fillMaxWidth()
        )
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