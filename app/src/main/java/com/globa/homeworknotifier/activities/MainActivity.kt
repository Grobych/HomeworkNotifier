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
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.globa.homeworknotifier.viewmodel.MainActivityViewModel
import com.globa.homeworknotifier.R
import com.globa.homeworknotifier.adapters.TaskAdapter
import com.globa.homeworknotifier.fragments.AddTaskDialogFragment
import com.globa.homeworknotifier.interfaces.AddTaskDialogInterface
import com.globa.homeworknotifier.model.Subject
import com.globa.homeworknotifier.model.Task
import java.sql.Date

class MainActivity() : FragmentActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}