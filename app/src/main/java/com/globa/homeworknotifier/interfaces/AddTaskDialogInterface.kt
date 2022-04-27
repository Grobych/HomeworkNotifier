package com.globa.homeworknotifier.interfaces

import com.globa.homeworknotifier.model.Task

interface AddTaskDialogInterface {
    fun sendTask(task: Task)
}