package com.example.teacher_helper.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacher_helper.data.MyDatabase
import com.example.teacher_helper.data.entities.StudentsSubjects
import com.example.teacher_helper.data.relations.StudentWithSubjects
import com.example.teacher_helper.data.relations.SubjectWithStudents
import com.example.teacher_helper.data.repositories.StudentsSubjectsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentsSubjectsViewModel(application: Application): AndroidViewModel(application) {


    private val repository: StudentsSubjectsRepository

    init{
        val studentsSubjectsDao = MyDatabase.getDatabase(application).studentsSubjectsDao()
        repository = StudentsSubjectsRepository(studentsSubjectsDao)

    }

    fun addStudentSubject(studentsSubjects: StudentsSubjects){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudentSubject(studentsSubjects)
        }
    }

    fun getSubjectWithStudents(currentSubject: Long): LiveData<SubjectWithStudents> {
        return repository.getSubjectsWithStudents(currentSubject)
    }

    fun getStudentsWithSubjects(currentStudent: Long): LiveData<StudentWithSubjects> {
        return repository.getStudentsWithSubjects(currentStudent)
    }

}