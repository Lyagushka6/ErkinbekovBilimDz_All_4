package com.example.erkinbekovbilimdz_all_4.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.erkinbekovbilimdz_all_4.R
import com.example.erkinbekovbilimdz_all_4.databinding.ItemTaskBinding
import com.example.erkinbekovbilimdz_all_4.model.Task

class TaskAdapter(val onLongClick:(Task) -> Unit, private val context : Context) : Adapter<TaskAdapter.TaskViewHolder>() {
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
            if (adapterPosition % 2 == 0){
                binding.root.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
                binding.tvDesc.setTextColor(ContextCompat.getColor(context, R.color.white))
                binding.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.white))
            }else {
                binding.root.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                binding.tvDesc.setTextColor(ContextCompat.getColor(context, R.color.black))
                binding.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.black))
            }
            binding.root.setOnLongClickListener {
                onLongClick(task)
                false
            }
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc
        }

    }
}