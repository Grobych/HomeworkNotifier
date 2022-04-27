package com.globa.homeworknotifier.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.globa.homeworknotifier.R
import com.globa.homeworknotifier.databinding.TaskRecyclerViewItemBinding
import com.globa.homeworknotifier.model.Task

class TaskAdapter(private val list : MutableList<Task>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    fun add(newTask: Task){
        list.add(newTask)
        notifyItemChanged(list.lastIndex)
    }
    fun delete(task: Task){
        if (list.remove(task)) notifyItemRemoved(list.lastIndex)
    }
    fun update(task: Task, position: Int){
        list[position] = task
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: TaskRecyclerViewItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.task_recycler_view_item, parent, false)
        return ViewHolder(binding)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(private val binding: TaskRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            // Define click listener for the ViewHolder's View.
        }

        fun bind(task: Task){
            binding.task = task
//            binding.taskTitleTextView.text = task.title
//            binding.taskDescriptionTextView.text = task.description
//            binding.taskDeadlineTextView.text = task.deadline.toString()
        }

    }
}