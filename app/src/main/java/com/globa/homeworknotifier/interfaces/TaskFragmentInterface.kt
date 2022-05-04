package com.globa.homeworknotifier.interfaces

import com.globa.homeworknotifier.model.Task

interface TaskFragmentInterface {
    fun to(task: Task)
}