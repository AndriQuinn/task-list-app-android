package com.example.tasklist.structure

import com.example.tasklist.R

enum class StatusType (
    val statusName: String,
    val icon: Int
) {
    DONE(
        statusName = "DONE",
        icon = R.drawable.done
    ),

    ONGOING(
        statusName = "ONGOING",
        icon = R.drawable.ongoing
    ),

    MISSEDTASK(
        statusName = "MISSED",
        icon = R.drawable.not_done
    )
}