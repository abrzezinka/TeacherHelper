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
import com.example.teacher_helper.data.entities.Student
import com.example.teacher_helper.data.viewmodels.StudentViewModel
import com.example.teacher_helper.databinding.FragmentAddStudentBinding


class AddStudent : Fragment(R.layout.fragment_add_student) {

    private lateinit var mStudentViewModel: StudentViewModel
    private var _binding: FragmentAddStudentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddStudentBinding.inflate(inflater, container, false)

        mStudentViewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        binding.floatingActionButton2.setOnClickListener{
            insertDataToDataBase()
        }

        return binding.root
    }


    private fun insertDataToDataBase() {
        val firstName = binding.nameEt.text.toString()
        val lastName = binding.lastnameEt.text.toString()
        val number = binding.idEt.text

        if(inputCheck(firstName, lastName, number!!)){
            val student = Student(0, firstName, lastName, Integer.parseInt(number.toString()))
            mStudentViewModel.addStudent(student)
            Toast.makeText(requireContext(), "Pomyślnie dodano", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addStudent2_to_studentsList)
        }else{
            Toast.makeText(requireContext(), "Niepoprawne dane", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String,lastName:String, id: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && id.isEmpty())
    }


}

