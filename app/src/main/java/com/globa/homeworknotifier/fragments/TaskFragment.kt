package com.globa.homeworknotifier.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.globa.homeworknotifier.databinding.TaskFragmentBinding
import com.globa.homeworknotifier.model.Task
import com.globa.homeworknotifier.viewmodel.TaskViewModel

class TaskFragment(val task: Task) : Fragment() {

    private lateinit var viewModel: TaskViewModel
    private lateinit var binding: TaskFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TaskFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        Toast.makeText(context,"$task",Toast.LENGTH_LONG).show()
        viewModel.taskLiveData.postValue(task)
        viewModel.taskLiveData.observe(viewLifecycleOwner,{
            binding.task = it
        })

        binding.taskDeleteButton.setOnClickListener {

        }
        binding.taskDoneButton.setOnClickListener {

        }

        super.onViewCreated(view, savedInstanceState)
    }



}