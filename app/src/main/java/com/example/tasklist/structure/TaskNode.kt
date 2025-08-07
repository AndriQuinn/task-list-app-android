package com.example.tasklist.structure

data class TaskNode (
    val title: String,
    val description: String,
    val deadline: String,
    val status: StatusType
)