package com.globa.homeworknotifier.viewmodel

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.globa.homeworknotifier.App
import com.globa.homeworknotifier.model.Task
import com.globa.homeworknotifier.model.TaskStatus

class MainActivityViewModel() : ViewModel() {
    val taskListLive = MutableLiveData<MutableList<Task>>()
    val taskLive = MutableLiveData<Task?>()
    private val repository = App.instance?.getRepository()!!
    init {
        taskListLive.value = repository.taskList
    }

    companion object{
        private lateinit var instance : MainActivityViewModel

        @MainThread
        fun getInstance(): MainActivityViewModel {
            instance = if(Companion::instance.isInitialized) instance else MainActivityViewModel()
            return instance
        }
    }

//    fun updateList(){
//        repository.loadFromDatabase()
//        taskListLive.postValue(repository.taskList)
//    }
//    fun updateList(status: TaskStatus){
//        repository.loadFromDatabase(status)
//        taskListLive.postValue(repository.taskList)
//    }
    fun add(task: Task){
        taskListLive.value?.add(task)
        taskListLive.postValue(taskListLive.value)
        Log.d("VIEW MODEL", "$task")
        repository.add(task)
    }

    fun doneCurrentTask(){
        taskLive.value?.let {
            it.status = TaskStatus.DONE
            repository.update(it) }
    }
    fun deleteCurrentTask(){
        taskLive.value?.let { repository.delete(it) }
        taskLive.value = null
    }
}