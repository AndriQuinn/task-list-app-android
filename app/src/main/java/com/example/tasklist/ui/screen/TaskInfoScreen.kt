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
        TaskInfoNavBar(backFunction = {navController.popBackStack()})
    }
}

@Composable
fun TaskInfoNavBar(
    backFunction: () -> Unit, // Back function callback
    modifier: Modifier = Modifier
) {
    // Container for navigation, horizontally placed
    Row (
        horizontalArrangement = Arrangement.Start,
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