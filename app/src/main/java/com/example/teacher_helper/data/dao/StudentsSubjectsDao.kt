package com.example.teacher_helper.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacher_helper.data.entities.StudentsSubjects
import com.example.teacher_helper.data.relations.StudentWithSubjects
import com.example.teacher_helper.data.relations.SubjectWithStudents


@Dao
interface StudentsSubjectsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStudentSubject(studentSubject: StudentsSubjects)

    @Transaction
    @Query("SELECT * FROM students WHERE studentId = :id")
    fun getStudentsWithSubjects(id: Long): LiveData<StudentWithSubjects>

    @Transaction
    @Query("SELECT * FROM subjects WHERE subjectId = :id")
    fun getSubjectsWithStudents(id: Long): LiveData<SubjectWithStudents>
}