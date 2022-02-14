package com.example.teacher_helper.data.repositories

import androidx.lifecycle.LiveData
import com.example.teacher_helper.data.dao.SubjectDao
import com.example.teacher_helper.data.entities.Subject

class SubjectRepository(private val subjectDao: SubjectDao) {
    val readAllData: LiveData<List<Subject>> = subjectDao.readAllData()

    suspend fun addSubject(subject: Subject){
        subjectDao.addSubject(subject)
    }

    suspend fun updateSubject(subject: Subject){
        subjectDao.updateSubject(subject)
    }

    suspend fun deleteSubject(subject: Subject){
        subjectDao.deleteSubject(subject)
    }

    suspend fun deleteAllSSubjects(){
        subjectDao.deleteAllSubjects()
    }

}