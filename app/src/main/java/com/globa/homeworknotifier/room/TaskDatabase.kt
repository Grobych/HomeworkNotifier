package com.globa.homeworknotifier.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.globa.homeworknotifier.model.Task
import com.globa.homeworknotifier.util.DateConverter

@Database(entities = [Task::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDao : TaskDao
}