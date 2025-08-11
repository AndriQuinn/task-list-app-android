package com.example.tasklist.structure

import kotlinx.serialization.Serializable

@Serializable
data class TaskNode (
    val title: String,
    val description: String,
    val deadline: String,
    val status: String,
    val id: String
)