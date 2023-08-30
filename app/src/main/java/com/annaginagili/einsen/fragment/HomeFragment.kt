package com.annaginagili.einsen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.annaginagili.einsen.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var add: ImageButton
    lateinit var doItNow: ConstraintLayout
    lateinit var about: ImageButton
    lateinit var decide: ConstraintLayout
    lateinit var delegate: ConstraintLayout
    lateinit var dump: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        add = binding.add
        doItNow = binding.doItNow
        about = binding.about
        decide = binding.decide
        delegate = binding.delegate
        dump = binding.dump

        add.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddFragment2())
        }

        doItNow.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAllTaskFragment())
        }

        about.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAboutFragment())
        }

        decide.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAllTaskFragment())
        }

        delegate.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAllTaskFragment())
        }

        dump.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAllTaskFragment())
        }

        return binding.root
    }
}