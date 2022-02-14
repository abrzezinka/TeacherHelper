package com.example.teacher_helper.data.repositories

import androidx.lifecycle.LiveData
import com.example.teacher_helper.data.dao.StudentsSubjectsDao
import com.example.teacher_helper.data.entities.StudentsSubjects
import com.example.teacher_helper.data.relations.StudentWithSubjects
import com.example.teacher_helper.data.relations.SubjectWithStudents

class StudentsSubjectsRepository(private val studentsSubjectsDao: StudentsSubjectsDao) {
    suspend fun addStudentSubject(studentsSubjects: StudentsSubjects){
        studentsSubjectsDao.addStudentSubject(studentsSubjects)
    }

    fun getSubjectsWithStudents(id: Long): LiveData<SubjectWithStudents>{
        return studentsSubjectsDao.getSubjectsWithStudents(id)
    }

    fun getStudentsWithSubjects(id: Long): LiveData<StudentWithSubjects>{
        return studentsSubjectsDao.getStudentsWithSubjects(id)
    }
}