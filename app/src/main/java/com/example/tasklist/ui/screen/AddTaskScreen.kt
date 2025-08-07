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

@Composable
fun AddTaskScreen(
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
        val context = LocalContext.current
        var taskTitle by remember { mutableStateOf("") } // Task title state holder
        var taskDescription by remember { mutableStateOf("") } // Task description state holder
        var taskDeadline by remember { mutableStateOf("") } // Task deadline state holder

        AddTaskNavBar(
            addFunction = { addTaskFile( // Function to add the task to json file
                context = context,
                taskTitle = taskTitle,
                taskDescription = taskDescription,
                taskDeadline = taskDeadline
            ) },
            backFunction = {navController.popBackStack()}, // Back button
            modifier = Modifier.weight(0.8f)
        )
        AddTaskBody(
            title = taskTitle, // Pass the state
            description = taskDescription, // Pass the state
            setTitleFunction = { title -> taskTitle = title }, // taskTitle setter
            setDeadlineFunction = { deadline -> taskDeadline = deadline }, // taskDeadline setter
            setDescriptionFunction = { description -> taskDescription = description }, // taskDescription setter
            modifier = Modifier.weight(9.8f)
        )
    }
}

@Composable
fun AddTaskNavBar(
    addFunction: () -> Unit, // Add function callback
    backFunction: () -> Unit, // Back function callback
    modifier: Modifier = Modifier
) {
    // Container for navigation, horizontally placed
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 15.dp)
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
        // Add button
        Button (
            onClick = {addFunction()}, // Use add function
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
fun AddTaskBody(
    title: String,
    description: String,
    setTitleFunction: (String) -> Unit,
    setDeadlineFunction: (String) -> Unit,
    setDescriptionFunction: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current // Get app context
    val calendar = Calendar.getInstance() // Get calendar
    val year = calendar.get(Calendar.YEAR) // Set default year
    val month = calendar.get(Calendar.MONTH) // Set default month
    val day = calendar.get(Calendar.DAY_OF_MONTH) // Set default day

    // Holds the selected date as state
    var selectedDate by remember { mutableStateOf("") }

    // Date picker
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, y, m, d -> // year, month, day parameters
                selectedDate = "$d/${m + 1}/$y" // Set the selected date
                setDeadlineFunction(selectedDate) // Return the selected date tot the parent
            },
            // Default values if user didnt pick any dates:
            year,
            month,
            day
        )
    }

    // Header
    Text(
        text = "New task",
        color = Color.White,
        fontSize = 25.sp,
        modifier = Modifier.padding(30.dp).fillMaxWidth()
    )

    // Container for task info field e.g. title, vertically placed
    Column (
        modifier = modifier.padding(30.dp).fillMaxSize()
    ) {
        // set title field
        TextField(
            value = title,
            onValueChange = {setTitleFunction(it)}, // Return the value to parent
            label = {Text("Title")},
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(10.dp))
        // set deadline field
        Button(
            onClick = {datePickerDialog.show()}, // Open calendar
            colors = buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(0.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            if (selectedDate.isBlank()) {
                Text(
                    text ="Pick a deadline",
                    color = Color.White,
                    modifier = modifier.padding(start = 10.dp).fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
            } else {
                Text(
                    text = selectedDate,
                    color = Color.White,
                    modifier = modifier.padding(start = 10.dp).fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
            }
        }
        Spacer(Modifier.height(10.dp))
        // set description field
        TextField(
            value = description,
            onValueChange = {setDescriptionFunction(it)}, // Return the value to parent
            label = {Text("Description")},
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,

            ),
            singleLine = false,
            modifier = Modifier.fillMaxWidth()
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
    AddTaskScreen(navController = rememberNavController())
}