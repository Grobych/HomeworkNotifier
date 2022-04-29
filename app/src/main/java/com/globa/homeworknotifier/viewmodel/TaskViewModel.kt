package com.globa.homeworknotifier.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.globa.homeworknotifier.model.Task

class TaskViewModel : ViewModel() {
    val taskLiveData = MutableLiveData<Task>()
}