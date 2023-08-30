package com.annaginagili.einsen.fragment.addFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.annaginagili.einsen.data.Task
import com.annaginagili.einsen.database.AppDatabase
import com.annaginagili.einsen.databinding.FragmentAddBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.floor

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: AddFragmentViewModel
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentAddBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[AddFragmentViewModel::class.java]

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

        importance.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                importanceText = floor(p1.toDouble()/20).toInt().toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        urgency.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
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
                CoroutineScope(Dispatchers.IO).launch {
                    db = Room.databaseBuilder(requireContext(), AppDatabase::class.java, "Task")
                        .build()

                    viewModel.addTask(db.taskDao(), Task(0, title.text.toString(),
                        description.text.toString(), category.text.toString(),
                        importanceText, urgencyText))
                }
                findNavController().navigate(AddFragmentDirections.actionAddFragment2ToHomeFragment())
            }
        }

        return binding.root
    }
}