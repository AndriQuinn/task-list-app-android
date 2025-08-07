package com.example.tasklist.functions

import android.content.Context
import java.io.File
import org.json.JSONArray;
import org.json.JSONObject;

fun toMonthName(month: String): String  = when (month.toInt()) {
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


    val filePath = File(context.filesDir,"task-list.json")

    if (!filePath.exists()) {
        filePath.writeText("[]")
    }
    val fileContent: JSONArray = JSONArray(filePath.readText())
    val taskObject = JSONObject()
    taskObject.put("taskTitle", taskTitle)
    taskObject.put("taskDeadline", taskDeadline)
    taskObject.put("taskDescription", taskDescription)

    fileContent.put(taskObject)
    filePath.writeText(fileContent.toString())
}