package com.globa.homeworknotifier.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.globa.homeworknotifier.R
import com.globa.homeworknotifier.databinding.TaskRecyclerViewItemBinding
import com.globa.homeworknotifier.interfaces.RecyclerViewClickListener
import com.globa.homeworknotifier.model.Task

class TaskAdapter(private val list : MutableList<Task>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    fun add(newTask: Task){
        list.add(newTask)
        notifyItemChanged(list.lastIndex)
    }
    fun delete(task: Task){
        if (list.remove(task)) notifyItemRemoved(list.lastIndex)
    }
    fun delete(i : Int){
        if (list.remove(list[i])) notifyItemChanged(i)
    }
    fun update(task: Task, position: Int){
        list[position] = task
        notifyItemChanged(position)
    }
    fun update(list: List<Task>){
        list.forEach {
            if (!this.list.contains(it)){
                this.list.add(it)
                notifyItemChanged(this.list.lastIndex)
            } else{
                val i = this.list.indexOf(it)
                this.list[i] = it
                notifyItemChanged(i)
            }
        }
    }
    fun get(i : Int) : Task{
        return list[i]
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

    inner class ViewHolder(private val binding: TaskRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(task: Task){
            binding.task = task
        }

        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }

    }
}