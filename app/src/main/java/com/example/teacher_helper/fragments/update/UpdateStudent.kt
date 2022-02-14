package com.example.teacher_helper.fragments.update

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.teacher_helper.R
import com.example.teacher_helper.data.viewmodels.StudentViewModel
import com.example.teacher_helper.data.entities.Student
import com.example.teacher_helper.databinding.FragmentUpdateStudentBinding

class UpdateStudent : Fragment() {

    private val args by navArgs<UpdateStudentArgs>()
    private lateinit var mStudentViewModel: StudentViewModel
    private var _binding: FragmentUpdateStudentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateStudentBinding.inflate(inflater, container, false)
        mStudentViewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        binding.editNameEt.setText(args.currentStudent.firstName)
        binding.editLastNameEt.setText(args.currentStudent.lastName)
        binding.editNumberEt.setText(args.currentStudent.number.toString())

        binding.editButton.setOnClickListener{
            updateItemInDataBase()
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateItemInDataBase() {
        val firstName = binding.editNameEt.text.toString()
        val lastName = binding.editLastNameEt.text.toString()
        val number = binding.editNumberEt.text

        if(inputCheck(firstName, lastName, number!!)){
            val updatedStudent = Student(args.currentStudent.studentId, firstName, lastName, Integer.parseInt(number.toString()))
            mStudentViewModel.updateStudent(updatedStudent)
            Toast.makeText(requireContext(), "Zaktualizowano", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_studentsList)
        }else{
            Toast.makeText(requireContext(), "Niepoprawnie wprowadzone dane", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String,lastName:String, id: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && id.isEmpty())
    }

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Tak"){_, _ ->
            mStudentViewModel.deleteStudent(args.currentStudent)
            Toast.makeText(requireContext(),
                "Usunięto ${args.currentStudent.firstName} ${args.currentStudent.lastName}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_studentsList)
        }

        builder.setNegativeButton("Nie"){_, _ ->}
        builder.setTitle("Usunąć ${args.currentStudent.firstName} ${args.currentStudent.lastName}?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}