package com.example.teacher_helper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.teacher_helper.databinding.FragmentHomePageBinding

class HomePage : Fragment() {

    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentHomePageBinding.inflate(inflater, container, false)


        binding.studentsBtn.setOnClickListener{
            findNavController().navigate(R.id.action_homePage_to_studentsList)
        }

        binding.subjectsBtn.setOnClickListener{
            findNavController().navigate(R.id.action_homePage_to_subjectsList)
        }


        return binding.root
    }

}