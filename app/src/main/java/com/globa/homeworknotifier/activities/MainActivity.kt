package com.globa.homeworknotifier.activities

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.globa.homeworknotifier.viewmodel.MainActivityViewModel
import com.globa.homeworknotifier.R
import com.globa.homeworknotifier.fragments.TaskFragment
import com.globa.homeworknotifier.fragments.TaskListFragment

class MainActivity() : FragmentActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var taskListFragment: TaskListFragment
    private lateinit var taskFragment: TaskFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        taskListFragment = TaskListFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.mainFragmentContainer,taskListFragment)
            .setReorderingAllowed(true)
            .commit()
    }
}