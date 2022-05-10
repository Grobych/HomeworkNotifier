package com.globa.homeworknotifier.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.globa.homeworknotifier.App
import com.globa.homeworknotifier.databinding.TaskFragmentBinding
import com.globa.homeworknotifier.interfaces.NoticeDialogListener
import com.globa.homeworknotifier.model.Task
import com.globa.homeworknotifier.model.TaskStatus
import com.globa.homeworknotifier.viewmodel.TaskViewModel

class TaskFragment(val task: Task) : Fragment(), NoticeDialogListener {

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
            val dialog = DeleteTaskDialogFragment(this)
            activity?.let { dialog.show(it.supportFragmentManager,"DeleteTaskDialog") }
        }
        binding.taskDoneButton.setOnClickListener {
            task.status = TaskStatus.DONE
            App.instance?.getRepository()?.done(task)
            Toast.makeText(context,"Done!",Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        App.instance?.getRepository()?.delete(task)
        parentFragmentManager.popBackStack()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {

    }


}