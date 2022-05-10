package com.globa.homeworknotifier

import androidx.room.Room
import android.app.Application
import com.globa.homeworknotifier.model.TaskRepository
import com.globa.homeworknotifier.model.TaskStatus
import com.globa.homeworknotifier.room.TaskDatabase

class App : Application() {
    private var database: TaskDatabase? = null
    private lateinit var repository: TaskRepository

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, TaskDatabase::class.java, "database")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        repository = TaskRepository
        repository.loadFromDatabase(TaskStatus.IN_PROGRESS)
    }

    fun getDatabase(): TaskDatabase? {
        return database
    }
    fun getRepository(): TaskRepository{
        return repository
    }

    companion object {
        var instance: App? = null
    }
}