package com.annaginagili.einsen.fragment.detailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.annaginagili.einsen.database.AppDatabase
import com.annaginagili.einsen.databinding.FragmentDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    lateinit var viewModel: DetailsFragmentViewModel
    lateinit var title: TextView
    lateinit var details: TextView
    lateinit var urgency: TextView
    lateinit var importance: TextView
    private val args by navArgs<DetailsFragmentArgs>()
    lateinit var back: ImageButton
    lateinit var delete: ImageButton
    lateinit var db: AppDatabase
    lateinit var edit: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentDetailsBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[DetailsFragmentViewModel::class.java]
        title = binding.title
        details = binding.details
        urgency = binding.urgency
        importance = binding.importance
        back = binding.arrow
        delete = binding.delete
        edit = binding.edit

        title.text = args.task.title
        details.text = args.task.description
        urgency.text = args.task.urgency
        importance.text = args.task.importance

        back.setOnClickListener {
            findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToAllTaskFragment())
        }

        delete.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db = Room.databaseBuilder(requireContext(), AppDatabase::class.java, "Task")
                    .build()
                viewModel.deleteTask(db.taskDao(), args.task)
            }

            findNavController().popBackStack()
        }

        edit.setOnClickListener {
            findNavController().navigate(DetailsFragmentDirections.
            actionDetailsFragmentToEditTaskFragment(args.task))
        }

        return binding.root
    }
}