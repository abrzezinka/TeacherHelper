package com.example.teacher_helper.data.repositories

import androidx.lifecycle.LiveData
import com.example.teacher_helper.data.dao.StudentDao
import com.example.teacher_helper.data.entities.Student

class StudentRepository (private val studentDao: StudentDao){
    val readAllData: LiveData<List<Student>> = studentDao.readAllData()

     suspend fun addStudent(student: Student){
        studentDao.addStudent(student)
    }

    suspend fun updateStudent(student: Student){
        studentDao.updateStudent(student)
    }

    suspend fun deleteStudent(student: Student){
        studentDao.deleteStudent(student)
    }

    suspend fun deleteAllStudents(){
        studentDao.deleteAllStudents()
    }
}