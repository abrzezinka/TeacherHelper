package com.example.teacher_helper.fragments.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teacher_helper.R
import com.example.teacher_helper.data.entities.StudentsSubjects
import com.example.teacher_helper.data.viewmodels.StudentViewModel
import com.example.teacher_helper.data.viewmodels.StudentsSubjectsViewModel
import com.example.teacher_helper.data.viewmodels.SubjectViewModel
import com.example.teacher_helper.databinding.FragmentStudentsListBinding

class StudentsList : Fragment() {

    private val args by navArgs<StudentsListArgs>()
    private lateinit var mStudentViewModel: StudentViewModel
    private lateinit var mStudentsSubjectsViewModel: StudentsSubjectsViewModel
    private var _binding: FragmentStudentsListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStudentsListBinding.inflate(inflater, container, false)
        val studentsView = binding.studentsListView
        val chooseView = args.viewType


        when(chooseView){
            "allStudents" -> {
                val adapter = StudentsListAdapter()

                // RecyclerView
                studentsView.adapter = adapter
                studentsView.layoutManager = GridLayoutManager(activity, 2)

                // StudentViewModel
                mStudentViewModel = ViewModelProvider(this)[StudentViewModel::class.java]

                mStudentViewModel.readAllData.observe(viewLifecycleOwner, Observer {
                        student -> adapter.setData(student)
                })

                binding.floatingActionButton.setOnClickListener{
                    findNavController().navigate(R.id.action_studentsList_to_addStudent2)
                }
            }

            "addStudent" -> {
                val addRelation : (Long) -> Unit = {
                        id ->
                    Log.d("", "działa")
                    mStudentsSubjectsViewModel.addStudentSubject(StudentsSubjects(args.subjectId,id))
                }
                val adapter = AddStudentToSubjectListAdapter(addRelation)

                studentsView.adapter = adapter
                adapter.subjectId = args.subjectId
                studentsView.layoutManager = LinearLayoutManager(requireContext())

                mStudentViewModel = ViewModelProvider(this)[StudentViewModel::class.java]
                mStudentsSubjectsViewModel = ViewModelProvider(this)[StudentsSubjectsViewModel::class.java]
                mStudentViewModel.readAllData.observe(viewLifecycleOwner, Observer {
                        student -> adapter.setData(student)
                })
            }

            "fromSubject" ->{
                val adapter = StudentsFromSubjectListAdapter()
                studentsView.adapter = adapter
                studentsView.layoutManager = LinearLayoutManager(requireContext())

                mStudentsSubjectsViewModel = ViewModelProvider(this)[StudentsSubjectsViewModel::class.java]

                mStudentsSubjectsViewModel.getSubjectWithStudents(args.subjectId).observe(viewLifecycleOwner, Observer {
                        subjectAndStudents -> adapter.setData(subjectAndStudents.students)
                })
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}