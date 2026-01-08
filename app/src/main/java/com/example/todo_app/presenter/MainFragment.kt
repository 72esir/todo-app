package com.example.todo_app.presenter

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_app.R
import com.example.todo_app.appComponent
import com.example.todo_app.data.models.Task
import com.example.todo_app.data.models.TaskStatus
import com.example.todo_app.databinding.FragmentMainBinding
import com.example.todo_app.di.viewModel.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dev.androidbroadcast.vbpd.viewBinding
import javax.inject.Inject
import kotlin.getValue

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    private val adapter = TasksAdapter(
        ::OnTaskStateChangeClick

    )

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding.tasksRecycler) {
            this@with.adapter = this@MainFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.tasks.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        binding.createTasks.setOnClickListener {
            val direction = MainFragmentDirections.actionMainFragmentToCreateTaskFragment()
            findNavController().navigate(direction)
        }
    }

    private fun OnTaskStateChangeClick(task: Task) {
        val states = TaskStatus.entries.map{it.name}.toTypedArray()
        var index = states.indexOf(task.status.name)
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Выберите состояние")
            .setNeutralButton("Отмена"){_,_ ->}
            .setPositiveButton("Окей") {dialog, newState ->
                val taskState = TaskStatus.entries.get(index)
                viewModel.changeTaskState(task, taskState)
            }
            .setSingleChoiceItems(states, index){ dialog, newState ->
                index = newState
            }
            .show()
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)

        super.onAttach(context)
    }
}