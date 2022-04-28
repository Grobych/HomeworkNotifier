package com.globa.homeworknotifier.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.globa.homeworknotifier.App
import com.globa.homeworknotifier.model.Task

class MainActivityViewModel : ViewModel() {

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
        taskLiveList.value =taskList
    }
    fun update(task: Task, pos : Int){
        taskList[pos] = task
        taskLiveList.value =taskList
    }

    fun loadFromDatabase(){
        taskLiveList.postValue(App.instance?.getDatabase()?.taskDao?.getAll()?.toMutableList())
    }

    fun <T> MutableLiveData<List<T>>.add(item: T) {
        val updatedItems = this.value as ArrayList
        updatedItems.add(item)
        this.value = updatedItems }

}