package com.example.tasklist.functions

import android.content.Context
import com.example.tasklist.structure.StatusType
import java.io.File
import org.json.JSONArray;
import org.json.JSONObject;

fun toMonthName(month: String): String  = when (month.toInt()) {
    // Convert month integers to month name
    0 ->  "Jan"
    1 ->  "Feb"
    2 ->  "Mar"
    3 ->  "Apr"
    4 ->  "May"
    5 ->  "Jun"
    6 ->  "Jul"
    7 ->  "Aug"
    8 ->  "Sep"
    9 ->  "Oct"
    10 ->  "Nov"
    11 ->  "Dec"
    else ->  ""
}

fun addTaskFile(context: Context, taskTitle: String, taskDeadline: String, taskDescription: String) {

    val filePath = File(context.filesDir,"task-list.json") // Get the file path

    // Checks if the file exist in the path, if not creaate one
    if (!filePath.exists()) {
        filePath.writeText("[]") // creates empty json array
    }
    val fileContent: JSONArray = JSONArray(filePath.readText()) // place the file content in the variable
    val taskObject = JSONObject() // Create json object to hold the task datas e.g. title
    taskObject.put("taskTitle", taskTitle)
    taskObject.put("taskDeadline", taskDeadline)
    taskObject.put("taskDescription", taskDescription)
    taskObject.put("taskStatus", "ONGOING")

    fileContent.put(taskObject) // put the task object to the json array
    filePath.writeText(fileContent.toString()) // rewrite the file with the updated one
}