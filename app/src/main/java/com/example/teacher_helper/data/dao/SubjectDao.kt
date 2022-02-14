package com.example.teacher_helper.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacher_helper.data.entities.Subject

@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSubject(subject: Subject)

    @Query("SELECT * FROM subjects ORDER BY subjectId ASC")
    fun readAllData(): LiveData<List<Subject>>

    @Update
    suspend fun updateSubject(subject: Subject)

    @Delete
    suspend fun deleteSubject(subject: Subject)

    @Query("DELETE FROM subjects")
    suspend fun deleteAllSubjects()

}