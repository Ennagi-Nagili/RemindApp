package com.annaginagili.einsen.fragment.editTask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.annaginagili.einsen.R
import com.annaginagili.einsen.data.Task
import com.annaginagili.einsen.database.AppDatabase
import com.annaginagili.einsen.databinding.FragmentAddBinding
import com.annaginagili.einsen.fragment.addFragment.AddFragmentDirections
import com.annaginagili.einsen.fragment.addFragment.AddFragmentViewModel
import com.annaginagili.einsen.fragment.detailsFragment.DetailsFragmentArgs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.floor

class EditTaskFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: EditTaskFragmentViewModel
    private lateinit var back: ImageButton
    private lateinit var title: EditText
    private lateinit var description: EditText
    private lateinit var category: EditText
    private lateinit var importance: SeekBar
    private lateinit var urgency: SeekBar
    private lateinit var save: Button
    private lateinit var db: AppDatabase
    var importanceText = "01"
    var urgencyText = "01"
    private val args by navArgs<EditTaskFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAddBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[EditTaskFragmentViewModel::class.java]

        back = binding.arrow
        title = binding.title
        description = binding.description
        category = binding.category
        importance = binding.importance
        urgency = binding.urgency
        save = binding.save

        back.setOnClickListener {
            findNavController().popBackStack()
        }

        title.setText(args.task.title)
        description.setText(args.task.description)
        category.setText(args.task.category)
        urgency.progress = args.task.urgency.replace("0", "").toInt() * 20
        importance.progress = args.task.importance.replace("0", "").toInt() * 20

        importance.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                importanceText = if (p1 < 100) {
                    "0" + floor(p1.toDouble()/20).toInt().toString()
                } else {
                    floor(p1.toDouble()/10).toInt().toString()
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        urgency.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                urgencyText = "0" + floor(p1.toDouble()/20).toInt().toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        save.setOnClickListener {
            if (title.text.isNotEmpty() && description.text.isNotEmpty() &&
                category.text.isNotEmpty()) {
                val task = Task(args.task.id, title.text.toString(), description.text.toString(),
                    category.text.toString(), importanceText, urgencyText)

                CoroutineScope(Dispatchers.IO).launch {
                    db = Room.databaseBuilder(requireContext(), AppDatabase::class.java, "Task")
                        .build()

                    viewModel.editTask(db.taskDao(), task)
                }
                findNavController().navigate(EditTaskFragmentDirections.
                actionEditTaskFragmentToDetailsFragment(task))
            }
        }

        return binding.root
    }
}