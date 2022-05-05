package com.globa.homeworknotifier.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.globa.homeworknotifier.App

object TaskRepository {
    private val taskList = mutableListOf<Task>()
    val taskLiveList = MutableLiveData(taskList)

    fun add(task: Task){
        if (!taskList.contains(task)){
            taskList.add(task)
            taskLiveList.value =taskList
        }

        App.instance?.getDatabase()?.taskDao?.insert(task)
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

    fun get(pos : Int) : Task{
        return taskLiveList.value?.get(pos)!!
    }

    fun loadFromDatabase(){
        App.instance?.getDatabase()?.taskDao?.getAll()?.toMutableList()?.let { taskList.addAll(it) }
        Log.d("LOAD", "$taskList")
        taskLiveList.postValue(taskList)
        Log.d("LOAD", "$taskList")
    }

    fun <T> MutableLiveData<List<T>>.add(item: T) {
        val updatedItems = this.value as ArrayList
        updatedItems.add(item)
        this.value = updatedItems }
}