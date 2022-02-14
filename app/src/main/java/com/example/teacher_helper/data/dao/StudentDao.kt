package com.example.teacher_helper.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacher_helper.data.entities.Student

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStudent(student: Student)

    @Query("SELECT * FROM students ORDeR BY studentId ASC")
    fun readAllData(): LiveData<List<Student>>

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM students")
    suspend fun deleteAllStudents()

}