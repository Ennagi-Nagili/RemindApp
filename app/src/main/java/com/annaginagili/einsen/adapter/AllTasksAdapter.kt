package com.annaginagili.einsen.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.annaginagili.einsen.R
import com.annaginagili.einsen.data.Task
import com.annaginagili.einsen.databinding.TaskLayoutBinding

class AllTasksAdapter(private val context: Context, private val itemList: List<Task>):
    RecyclerView.Adapter<AllTasksAdapter.ItemHolder>() {
    private lateinit var listener: OnItemClickListener

    class ItemHolder(private val binding: TaskLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: Task, listener: OnItemClickListener) {
            binding.title.text = item.title
            binding.type.text = item.category

            binding.root.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = TaskLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(itemList[position], listener)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}