package com.globa.homeworknotifier.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.globa.homeworknotifier.R
import com.globa.homeworknotifier.adapters.TaskAdapter
import com.globa.homeworknotifier.interfaces.AddTaskDialogInterface
import com.globa.homeworknotifier.interfaces.RecyclerViewClickListener
import com.globa.homeworknotifier.model.Task
import com.globa.homeworknotifier.interfaces.TaskTouchListener
import com.globa.homeworknotifier.viewmodel.MainActivityViewModel

class TaskListFragment() : Fragment(), AddTaskDialogInterface {

    lateinit var recyclerView: RecyclerView
    lateinit var addTaskButton: Button

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.task_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = MainActivityViewModel.getInstance()

        recyclerView = view.findViewById(R.id.taskRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModel.taskListLive.value?.let { TaskAdapter(it) }
        recyclerView.addOnItemTouchListener(
            TaskTouchListener(requireActivity(), object : RecyclerViewClickListener {
                override fun recyclerViewListClicked(v: View, pos: Int) {
                    //toTaskFragment(viewModel.get(pos))
                }

                override fun recyclerViewListClicked(v: View, pos: Int, task: Task) {
                    viewModel.taskLive.value = task
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.mainFragmentContainer,TaskFragment())
                        .setReorderingAllowed(true)
                        .addToBackStack("task")
                        .commit()
                }
            })
        )

        addTaskButton = view.findViewById(R.id.addTaskButton)
        addTaskButton.setOnClickListener {
            addTask()
        }
        viewModel.taskListLive.observe(viewLifecycleOwner,{
            (recyclerView.adapter as TaskAdapter).update(it)
        })

        super.onViewCreated(view, savedInstanceState)
    }

    private fun addTask(){
        val newFragment = AddTaskDialogFragment()
        newFragment.taskInterface = this
        activity?.let { newFragment.show(it.supportFragmentManager,"AddTaskDialog") }
    }

    override fun sendTask(task: Task) {
        viewModel.add(task)
    }


}