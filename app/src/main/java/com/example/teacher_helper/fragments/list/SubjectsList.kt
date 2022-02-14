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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teacher_helper.R
import com.example.teacher_helper.data.entities.StudentsSubjects
import com.example.teacher_helper.data.viewmodels.StudentsSubjectsViewModel
import com.example.teacher_helper.data.viewmodels.SubjectViewModel
import com.example.teacher_helper.databinding.FragmentSubjectsListBinding

class SubjectsList : Fragment() {

    private val args by navArgs<SubjectsListArgs>()
    private lateinit var mSubjectViewModel: SubjectViewModel
    private lateinit var mStudentsSubjectsViewModel: StudentsSubjectsViewModel
    private var _binding: FragmentSubjectsListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSubjectsListBinding.inflate(inflater, container, false)
        val subjectView = binding.subjectsListView

        val chooseView = args.viewType
        when (chooseView){
            "allSubjects" ->{
                val adapter = SubjectsListAdapter()

                subjectView.adapter = adapter
                subjectView.layoutManager = LinearLayoutManager(requireContext())

                mSubjectViewModel = ViewModelProvider(this)[SubjectViewModel::class.java]
                mSubjectViewModel.readAllData.observe(viewLifecycleOwner, Observer {
                        subject -> adapter.setData(subject)

                })
                binding.floatingActionButton3.setOnClickListener{
                    findNavController().navigate(R.id.action_subjectsList_to_addSubject)
                }
            }

            "addSubject" ->{
                val addRelation : (Long) -> Unit = {
                    id ->Log.d("", "działa")
                    mStudentsSubjectsViewModel.addStudentSubject(StudentsSubjects(args.studentId,id))
                }

                val adapter = AddSubjectToStudentListAdapter(addRelation)

                subjectView.adapter = adapter
                adapter.studentId = args.studentId
                subjectView.layoutManager = LinearLayoutManager(requireContext())

                mSubjectViewModel = ViewModelProvider(this)[SubjectViewModel::class.java]
                mStudentsSubjectsViewModel = ViewModelProvider(this)[StudentsSubjectsViewModel::class.java]
                mSubjectViewModel.readAllData.observe(viewLifecycleOwner, Observer {
                        subject -> adapter.setData(subject)
                })
            }

            "fromStudent" ->{
                val adapter = SubjectsOfStudentListAdapter()
                subjectView.adapter = adapter
                subjectView.layoutManager = LinearLayoutManager(requireContext())

                mStudentsSubjectsViewModel = ViewModelProvider(this)[StudentsSubjectsViewModel::class.java]

                mStudentsSubjectsViewModel.getStudentsWithSubjects(args.studentId).observe(viewLifecycleOwner, Observer {
                        studentAndSubjects -> adapter.setData(studentAndSubjects.subjects)
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