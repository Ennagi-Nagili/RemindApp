package com.annaginagili.einsen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.annaginagili.einsen.R
import com.annaginagili.einsen.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    lateinit var binding: FragmentAboutBinding
    lateinit var back: ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentAboutBinding.inflate(inflater)
        back = binding.arrow

        back.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}