package com.globa.homeworknotifier.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.globa.homeworknotifier.databinding.TaskFragmentBinding
import com.globa.homeworknotifier.interfaces.NoticeDialogListener
import com.globa.homeworknotifier.viewmodel.MainActivityViewModel

class TaskFragment() : Fragment(), NoticeDialogListener {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: TaskFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TaskFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = MainActivityViewModel.getInstance()
        Toast.makeText(context,"${viewModel.taskLive.value}",Toast.LENGTH_SHORT).show()
        viewModel.taskLive.observe(viewLifecycleOwner,{
            binding.task = it
        })

        binding.taskDeleteButton.setOnClickListener {
            val dialog = DeleteTaskDialogFragment(this)
            activity?.let { dialog.show(it.supportFragmentManager,"DeleteTaskDialog") }
        }
        binding.taskDoneButton.setOnClickListener {
            viewModel.doneCurrentTask()
            Toast.makeText(context,"Done!",Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        viewModel.deleteCurrentTask()
        parentFragmentManager.popBackStack()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {

    }


}