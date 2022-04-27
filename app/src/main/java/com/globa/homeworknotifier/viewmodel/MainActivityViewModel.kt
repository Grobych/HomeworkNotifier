package com.globa.homeworknotifier.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.globa.homeworknotifier.model.Task

class MainActivityViewModel : ViewModel() {

    private val taskList = mutableListOf<Task>()
    val taskLiveList = MutableLiveData(taskList)

    fun add(task: Task){
        if (!taskList.contains(task)){
            taskList.add(task)
            taskLiveList.postValue(taskList)
        }
    }
    fun delete(task: Task){
        taskList.remove(task)
        taskLiveList.postValue(taskList)
    }
    fun update(task: Task, pos : Int){
        taskList[pos] = task
        taskLiveList.postValue(taskList)
    }

    fun loadFromDatabase(){

    }

}