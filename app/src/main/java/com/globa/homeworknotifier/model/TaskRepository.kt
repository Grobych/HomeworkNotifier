package com.globa.homeworknotifier.model

import androidx.lifecycle.MutableLiveData
import com.globa.homeworknotifier.App

object TaskRepository {
    private val taskList = mutableListOf<Task>()
    val taskLiveList = MutableLiveData(taskList)

    fun add(task: Task){
        val id = App.instance?.getDatabase()?.taskDao?.insert(task)
        task.id = id!!
        if (!taskList.contains(task)){
            taskList.add(task)
            taskLiveList.value =taskList
        }
    }
    fun delete(task: Task){
        taskList.remove(task)
        taskLiveList.postValue(taskList)
        App.instance?.getDatabase()?.taskDao?.delete(task)
    }
    fun update(task: Task, pos : Int){
        taskList[pos] = task
        taskLiveList.postValue(taskList)
        App.instance?.getDatabase()?.taskDao?.update(task)
    }
    fun update(update: Task){
        taskList.forEachIndexed{index, task ->
            task.takeIf { it.id == update.id }?.let { taskList[index] = update }
        }
        taskLiveList.postValue(taskList)
        App.instance?.getDatabase()?.taskDao?.update(update)
    }
    fun done(update: Task){
        taskList.forEachIndexed{ _, task ->
            task.takeIf { it.id == update.id }?.let { taskList.remove(it) }
        }
        taskLiveList.postValue(taskList)
        App.instance?.getDatabase()?.taskDao?.update(update)
    }

    fun get(pos : Int) : Task{
        return taskLiveList.value?.get(pos)!!
    }

    fun loadFromDatabase(){
        App.instance?.getDatabase()?.taskDao?.getAll()?.toMutableList()?.let { taskList.addAll(it) }
        taskLiveList.postValue(taskList)
    }
    fun loadFromDatabase(status: TaskStatus){
        App.instance?.getDatabase()?.taskDao?.getByStatus(status)?.toMutableList()?.let { taskList.addAll(it) }
        taskLiveList.postValue(taskList)
    }
}