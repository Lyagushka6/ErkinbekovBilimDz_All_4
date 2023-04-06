package com.example.erkinbekovbilimdz_all_4.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.erkinbekovbilimdz_all_4.databinding.ItemTaskBinding
import com.example.erkinbekovbilimdz_all_4.model.Task

class TaskAdapter(val onLongClick:(Task) -> Unit) : Adapter<TaskAdapter.TaskViewHolder>() {
    private val data: ArrayList<Task> = arrayListOf()

    fun addTask(task: Task){
        data.add(0,task)
        notifyItemChanged(0)
    }

    fun addTasks(task: List<Task>){
        data.clear()
        data.addAll(task)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root){
        fun bind(task: Task) {
            binding.root.setOnLongClickListener {
                onLongClick(task)
                false
            }
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc
        }

    }
}