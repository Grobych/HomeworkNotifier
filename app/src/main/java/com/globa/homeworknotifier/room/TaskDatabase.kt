package com.globa.homeworknotifier.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.globa.homeworknotifier.model.Task
import com.globa.homeworknotifier.room.converters.DateConverter
import com.globa.homeworknotifier.room.converters.TaskStatusConverter

@Database(entities = [Task::class], version = 2)
@TypeConverters(DateConverter::class, TaskStatusConverter::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDao : TaskDao
}