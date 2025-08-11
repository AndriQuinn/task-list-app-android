package com.example.tasklist.functions

import android.content.Context
import com.example.tasklist.structure.TaskNode
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

fun toMonthName(month: String): String  = when (month.toInt()) {
    // Convert month integers to month name
    1 ->  "Jan"
    2 ->  "Feb"
    3 ->  "Mar"
    4 ->  "Apr"
    5 ->  "May"
    6 ->  "Jun"
    7 ->  "Jul"
    8 ->  "Aug"
    9 ->  "Sep"
    10 ->  "Oct"
    11 ->  "Nov"
    12 ->  "Dec"
    else ->  ""
}

fun addTaskFile(context: Context, taskTitle: String, taskDeadline: String, taskDescription: String) {

    val filePath = File(context.filesDir,"task-list.json") // Get the file path

    // Checks if the file exist in the path, if not create one
    if (!filePath.exists()) {
        filePath.writeText("[]") // creates empty json array
        val file = JSONArray(filePath.readText())
        val length = JSONObject().put("length","0") // Create a json object length for id's
        file.put(length)
        filePath.writeText(file.toString())
    }
    val fileContent = JSONArray(filePath.readText()) // place the file content in the variable
    var length = fileContent.getJSONObject(0).getInt("length") // Get the value of length
    length++ // Iterate length
    val taskObject = JSONObject() // Create json object to hold the task data e.g. title
    taskObject.put("title", taskTitle)
    taskObject.put("deadline", taskDeadline)
    taskObject.put("description", taskDescription)
    taskObject.put("status", "ONGOING")
    taskObject.put("id", "$length") // Assign the id as the current length

    fileContent.put(taskObject) // put the task object to the json array
    fileContent.getJSONObject(0).put("length","$length") // Update the length on the file
    filePath.writeText(fileContent.toString()) // rewrite the file with the updated one
}

fun getTotal(list: MutableList<TaskNode>,type: String): Int {
    var total = 0
    for (tasks in list) {
        if (tasks.status == type) {
            total += 1
        }
    }
    return total
}

fun updatePastDeadlines(currentDate: String, context: Context) {
    val filePath = File(context.filesDir,"task-list.json")

    // Store the content
    val updatedList = if (filePath.exists()) {
        JSONArray(filePath.readText())
    } else { JSONArray() }

    // Check if empty, if empty stop the function
    if (updatedList.length() == 1) {
        return
    }

    // Iterate through the task
    for (task in 1 until  updatedList.length()) {
        // Check all tasks that are not missed yet
        if (updatedList.getJSONObject(task).getString("status") != "MISSED") {
            val deadline = updatedList.getJSONObject(task).getString("deadline").split("/") // Get the deadline
            val date = currentDate.split("/") // Get the current date

            if (date[1].toInt() > deadline[1].toInt()) { // Check if the current date is 1 month late than the deadline
                updatedList.getJSONObject(task).put("status","MISSED") // Update the status
            }
            else if (date[1].toInt() == deadline[1].toInt() && date[2].toInt() > deadline[2].toInt()) { // Check if the current date if the day is past deadline
                updatedList.getJSONObject(task).put("status","MISSED") // Update status
            }
        }
    }

    // Overwrites the file with the updated one
    filePath.writeText(updatedList.toString())
}

// Automatically removes file task after a month over the deadline
fun removeFileTask(currentDate: String,context: Context) {
    val date = currentDate.split("/") // Get date array
    val filePath = File(context.filesDir, "task-list.json") // Declare file path
    // Checks if file exists
    val file = if (filePath.exists()) {
        JSONArray(filePath.readText())
    } else { JSONArray() }


    // If file holds a task
    if (file.length() > 1) {
        for (task in 1 until file.length()) {
            val deadline = file.getJSONObject(task).get("deadline").toString().split("/") // Get deadline

            // Checks if the deadline is 1 month over the deadline
            if (deadline[1].toInt() > date[1].toInt() && deadline[2].toInt() > date[2].toInt()) {
                file.remove(task)
            }
        }
    } else {
        return
    }

    // Update the file
    filePath.writeText(file.toString())
}

fun markTaskDone(id: Int,context: Context) {
    val filePath = File(context.filesDir, "task-list.json") // Declare file path
    // Checks if file exists
    val file = if (filePath.exists()) {
        JSONArray(filePath.readText())
    } else { JSONArray() }

    if (file.length() > 1) {
        for (task in 1 until file.length()) {
            if (file.getJSONObject(task).get("id").toString().toInt() == id) {
                file.getJSONObject(task).put("status","DONE")
                break
            }
        }
    }
}