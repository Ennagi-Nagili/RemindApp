package com.annaginagili.einsen.fragment.allTasks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.annaginagili.einsen.R
import com.annaginagili.einsen.adapter.AllTasksAdapter
import com.annaginagili.einsen.data.Task
import com.annaginagili.einsen.database.AppDatabase
import com.annaginagili.einsen.databinding.FragmentAllTaskBinding
import com.annaginagili.einsen.fragment.addFragment.AddFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllTaskFragment : Fragment() {
    lateinit var binding: FragmentAllTaskBinding
    private lateinit var viewModel: AllTasksViewModel
    lateinit var back: ImageButton
    lateinit var taskRecycler: RecyclerView
    lateinit var db: AppDatabase
    private lateinit var tasks: List<Task>
    lateinit var add: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllTaskBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[AllTasksViewModel::class.java]

        back = binding.arrow
        add = binding.add

        back.setOnClickListener {
            findNavController().navigate(AllTaskFragmentDirections.actionAllTaskFragmentToHomeFragment())
        }

        add.setOnClickListener {
            findNavController().navigate(AllTaskFragmentDirections.actionAllTaskFragmentToAddFragment2())
        }

        CoroutineScope(Dispatchers.IO).launch {
            db = Room.databaseBuilder(requireContext(), AppDatabase::class.java, "Task")
                .build()
            viewModel.getTasks(db.taskDao())
        }

        taskRecycler = binding.tasks
        tasks = listOf()

        taskRecycler.setHasFixedSize(true)
        taskRecycler.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        setUpObservers()

        return binding.root
    }

    private fun setUpObservers(){
        viewModel.observeTasks().observe(viewLifecycleOwner) { tasks ->
            val adapter = AllTasksAdapter(requireContext(), tasks)
            taskRecycler.adapter = adapter
            adapter.setOnItemClickListener(object : AllTasksAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    findNavController().navigate(AllTaskFragmentDirections.actionAllTaskFragmentToDetailsFragment(
                        tasks[position]
                    ))
                }
            })
        }
    }
}