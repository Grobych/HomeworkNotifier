package com.globa.homeworknotifier.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.globa.homeworknotifier.R
import com.globa.homeworknotifier.model.Task
import com.globa.homeworknotifier.viewmodel.TaskViewModel

class TaskFragment(task : Task) : Fragment() {

    private var viewModel: TaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

    init {
        viewModel.taskLiveData.postValue(task)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.task_fragment, container, false)
    }
}