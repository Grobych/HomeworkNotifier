package com.globa.homeworknotifier.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.globa.homeworknotifier.R
import com.globa.homeworknotifier.adapters.TaskAdapter
import com.globa.homeworknotifier.interfaces.AddTaskDialogInterface
import com.globa.homeworknotifier.interfaces.RecyclerViewClickListener
import com.globa.homeworknotifier.interfaces.TaskFragmentInterface
import com.globa.homeworknotifier.model.Task
import com.globa.homeworknotifier.interfaces.TaskTouchListener
import com.globa.homeworknotifier.viewmodel.TaskListViewModel

class TaskListFragment(val taskFragmentInterface: TaskFragmentInterface) : Fragment(), AddTaskDialogInterface {

    lateinit var recyclerView: RecyclerView
    lateinit var addTaskButton: Button

    private lateinit var viewModel: TaskListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.task_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[TaskListViewModel::class.java]

        recyclerView = view.findViewById(R.id.taskRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = TaskAdapter(mutableListOf())
        recyclerView.addOnItemTouchListener(
            TaskTouchListener(requireActivity(), object : RecyclerViewClickListener {
                override fun recyclerViewListClicked(v: View, pos: Int) {
                    //toTaskFragment(viewModel.get(pos))
                }

                override fun recyclerViewListClicked(v: View, pos: Int, task: Task) {
                    taskFragmentInterface.to(task)
                    Toast.makeText(context,"$task",Toast.LENGTH_LONG).show()
                }
            })
        )

        addTaskButton = view.findViewById(R.id.addTaskButton)
        addTaskButton.setOnClickListener {
            addTask()
        }

        viewModel.taskLiveList.observe(viewLifecycleOwner,{
            (recyclerView.adapter as TaskAdapter).update(it)
        })
        if (savedInstanceState == null) viewModel.loadFromDatabase()



        super.onViewCreated(view, savedInstanceState)
    }

    private fun addTask(){
        val newFragment = AddTaskDialogFragment()
        newFragment.taskInterface = this
        activity?.let { newFragment.show(it.supportFragmentManager,"AddTaskDialog") }
    }

    override fun sendTask(task: Task) {
        viewModel.add(task)
//        (recyclerView.adapter as TaskAdapter).add(task)
    }


}