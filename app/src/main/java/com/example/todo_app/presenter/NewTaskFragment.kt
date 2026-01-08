package com.example.todo_app.presenter

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todo_app.R
import com.example.todo_app.appComponent
import com.example.todo_app.databinding.FragmentNewTaskBinding
import com.example.todo_app.di.viewModel.ViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import dev.androidbroadcast.vbpd.viewBinding
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import kotlin.getValue

class NewTaskFragment: Fragment(R.layout.fragment_new_task) {
    private val binding: FragmentNewTaskBinding by viewBinding(FragmentNewTaskBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: NewTaskViewModel by viewModels { viewModelFactory }

    private val datePicker = MaterialDatePicker.Builder.datePicker()
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        .build()

    private val dateFormatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newTaskToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.newTaskButtonPickDate.setOnClickListener {
            datePicker.show(childFragmentManager, null)
        }

        datePicker.addOnPositiveButtonClickListener {
            viewModel.saveDeadline(it)
            binding.newTaskDateInput.setText(dateFormatter.format(it))
        }

        binding.newTaskAddButton.setOnClickListener {
            val created = viewModel.createTask(
                title = binding.newTaskTitle.editText?.text.toString(),
                description=binding.newTaskDescription.editText?.text.toString()
            )
            if (created) {
                findNavController().popBackStack()
            }
        }
    }
    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }
}
