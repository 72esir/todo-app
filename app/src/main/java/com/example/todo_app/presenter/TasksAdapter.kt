package com.example.todo_app.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_app.data.models.Task
import com.example.todo_app.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TasksAdapter(
    private val onTaskStateChangeClick: (Task) -> Unit,
) : ListAdapter<Task, TasksAdapter.TaskViewHolder>(TaskDiffUtil()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(
            onTaskStateChangeClick,
            binding
        )
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    class TaskViewHolder(
        private val onTaskStateChangeClick: (Task) -> Unit,
        private val binding: ItemTaskBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) = with(binding) {
            val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            itemTaskTitle.text = task.title
            itemTaskDescription.text = task.description
            itemTaskStatus.text = task.status.name
            itemTaskStatus.setOnClickListener {
                onTaskStateChangeClick(task)
            }
            itemDeadline.text = formatter.format(task.deadline)
        }
    }

    private class TaskDiffUtil : DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
            oldItem == newItem


        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean =
            oldItem == newItem

    }
}
