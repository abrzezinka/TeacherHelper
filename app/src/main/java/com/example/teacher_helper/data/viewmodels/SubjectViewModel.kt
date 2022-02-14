package com.example.teacher_helper.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacher_helper.data.MyDatabase
import com.example.teacher_helper.data.entities.Subject
import com.example.teacher_helper.data.repositories.SubjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubjectViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Subject>>
    private val repository: SubjectRepository

    init {
        val subjectDao = MyDatabase.getDatabase(application).subjectDao()
        repository = SubjectRepository(subjectDao)
        readAllData = repository.readAllData
    }

    fun addSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSubject(subject)
        }
    }

    fun updateSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateSubject(subject)
        }
    }

    fun deleteSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteSubject(subject)
        }
    }

    fun deleteAllSubjects(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllSSubjects()
        }
    }


}