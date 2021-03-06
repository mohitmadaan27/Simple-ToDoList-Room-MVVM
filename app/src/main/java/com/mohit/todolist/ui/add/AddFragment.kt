package com.mohit.todolist.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mohit.todolist.R
import com.mohit.todolist.databinding.FragmentAddBinding
import com.mohit.todolist.utils.hideKeyboard
import com.mohit.todolist.utils.shortToast
import com.mohit.todolist.viewmodel.TodoViewModel
import com.mohit.todolist.viewmodel.TodoViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentAddBinding.inflate(inflater)
        val viewModelFactory = TodoViewModelFactory.getInstance(requireContext())
        val todoViewModel = ViewModelProvider(this, viewModelFactory)[TodoViewModel::class.java]

        binding.submitButton.setOnClickListener {
            val title = binding.title.text.toString()
            val description = binding.description.text.toString()

            if (title.isNotBlank() && description.isNotBlank()) {
                todoViewModel.addTodo(title, description)
                activity?.hideKeyboard()
                findNavController().popBackStack()
            } else {
                context?.shortToast(getString(R.string.fill_all_fields))
            }
        }

        return binding.root
    }

}
