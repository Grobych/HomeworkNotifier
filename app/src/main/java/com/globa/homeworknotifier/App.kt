package com.globa.homeworknotifier

import androidx.room.Room
import android.app.Application
import com.globa.homeworknotifier.room.TaskDatabase

class App : Application() {
    private var database: TaskDatabase? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, TaskDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    fun getDatabase(): TaskDatabase? {
        return database
    }

    companion object {
        var instance: App? = null
    }
}