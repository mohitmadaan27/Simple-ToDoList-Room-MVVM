package com.mohit.todolist.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mohit.todolist.R
import com.mohit.todolist.databinding.FragmentEditBinding
import com.mohit.todolist.utils.hideKeyboard
import com.mohit.todolist.utils.shortToast
import com.mohit.todolist.viewmodel.TodoViewModel
import com.mohit.todolist.viewmodel.TodoViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class EditFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mTodo = EditFragmentArgs.fromBundle(requireArguments()).todo
        // Inflate the layout for this fragment
        val binding = FragmentEditBinding.inflate(inflater).apply {
            todo = mTodo
        }

        val viewModelFactory = TodoViewModelFactory.getInstance(requireContext())
        val todoViewModel = ViewModelProvider(this, viewModelFactory)[TodoViewModel::class.java]

        binding.submitButton.setOnClickListener {
            val updatedTitle = binding.title.text.toString()
            val updatedDesc = binding.description.text.toString()

            if (updatedTitle.isNotBlank() && updatedDesc.isNotBlank()) {
                todoViewModel.updateTodo(mTodo!!.id, updatedTitle, updatedDesc, mTodo.checked)
                activity?.hideKeyboard()
                findNavController().popBackStack()
            } else {
                context?.shortToast(getString(R.string.fill_all_fields))
            }
        }

        return binding.root
    }

}
