package com.example.teacher_helper.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.teacher_helper.R
import com.example.teacher_helper.data.entities.Subject
import com.example.teacher_helper.data.viewmodels.SubjectViewModel
import com.example.teacher_helper.databinding.FragmentAddSubjectBinding

class AddSubject : Fragment() {

    private lateinit var mSubjectViewModel: SubjectViewModel
    private var _binding: FragmentAddSubjectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddSubjectBinding.inflate(inflater, container, false)

        mSubjectViewModel = ViewModelProvider(this)[SubjectViewModel::class.java]


        binding.addSubjectBtn.setOnClickListener{
            insertDataToDataBase()
        }

        return binding.root
    }

    private fun insertDataToDataBase() {
        val name = binding.nameEt.text.toString()

        if(inputCheck(name)){
            val subject = Subject(0, name)
            mSubjectViewModel.addSubject(subject)
            Toast.makeText(requireContext(), "Pomyślnie dodano", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addSubject_to_subjectsList)
        }else{
            Toast.makeText(requireContext(), "Niepoprawne dane", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String): Boolean{
        return !(TextUtils.isEmpty(name))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
