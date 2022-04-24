package com.globa.homeworknotifier.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.globa.homeworknotifier.model.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDao : TaskDao
}