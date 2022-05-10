package com.globa.homeworknotifier.room.converters

import androidx.room.TypeConverter
import com.globa.homeworknotifier.model.TaskStatus

object TaskStatusConverter {
    @TypeConverter
    fun toTaskStatus(string: String): TaskStatus {
        return TaskStatus.valueOf(string)
    }

    @TypeConverter
    fun fromTaskStatus(status: TaskStatus): String {
        return status.toString()
    }
}