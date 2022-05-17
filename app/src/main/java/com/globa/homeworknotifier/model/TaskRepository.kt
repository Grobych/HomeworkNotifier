package com.globa.homeworknotifier.model

import android.util.Log
import com.globa.homeworknotifier.App

object TaskRepository {
    val taskList = mutableListOf<Task>()
    private val database = App.instance?.getDatabase()!!


    fun add(task: Task){
        Log.d("REPO", "$task")
        val id = App.instance?.getDatabase()?.taskDao?.insert(task)
        task.id = id!!
        if (!taskList.contains(task)){
            taskList.add(task)
        }
        Log.d("REPO", "$database")
        database.taskDao.insert(task)
    }
    fun delete(task: Task){
        taskList.remove(task)
        database.taskDao.delete(task)
    }
    fun update(task: Task, pos : Int){
        taskList[pos] = task
        database.taskDao.update(task)
    }
    fun update(update: Task){
        taskList.forEachIndexed{index, task ->
            task.takeIf { it.id == update.id }?.let { taskList[index] = update }
        }
        database.taskDao.update(update)
    }
    fun done(update: Task){
        taskList.forEachIndexed{ _, task ->
            task.takeIf { it.id == update.id }?.let { taskList.remove(it) }
        }
        database.taskDao.update(update)
    }

    fun get(pos : Int) : Task{
        return taskList[pos]
    }

    fun loadFromDatabase(){
        database.taskDao.getAll().toMutableList().let { taskList.addAll(it) }
    }
    fun loadFromDatabase(status: TaskStatus){
        Log.d("REPO", "$database")
        database.taskDao.getByStatus(status).toMutableList().let { taskList.addAll(it) }
    }
}