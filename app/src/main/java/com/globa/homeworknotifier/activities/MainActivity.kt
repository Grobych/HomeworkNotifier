package com.globa.homeworknotifier.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.globa.homeworknotifier.viewmodel.MainActivityViewModel
import com.globa.homeworknotifier.R
import com.globa.homeworknotifier.adapters.TaskAdapter
import com.globa.homeworknotifier.fragments.AddTaskDialogFragment
import com.globa.homeworknotifier.fragments.TaskFragment
import com.globa.homeworknotifier.fragments.TaskListFragment
import com.globa.homeworknotifier.interfaces.AddTaskDialogInterface
import com.globa.homeworknotifier.interfaces.TaskFragmentInterface
import com.globa.homeworknotifier.model.Subject
import com.globa.homeworknotifier.model.Task
import java.sql.Date

class MainActivity() : FragmentActivity(), TaskFragmentInterface {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var taskListFragment: TaskListFragment
    private lateinit var taskFragment: TaskFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        taskListFragment = TaskListFragment(this)
        supportFragmentManager.beginTransaction()
            .add(R.id.mainFragmentContainer,taskListFragment)
            .commit()
    }

    private fun toFragment(task: Task){
        taskFragment = TaskFragment(task)
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragmentContainer, taskFragment)
            .commit()
    }

    override fun to(task: Task) {
        Log.d("TO", "$task")
        toFragment(task)
    }

}