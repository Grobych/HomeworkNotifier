package com.globa.homeworknotifier.interfaces

import android.view.View
import com.globa.homeworknotifier.model.Task

interface RecyclerViewClickListener {
    fun recyclerViewListClicked(v : View, pos : Int)
    fun recyclerViewListClicked(v : View, pos : Int, task: Task)
}