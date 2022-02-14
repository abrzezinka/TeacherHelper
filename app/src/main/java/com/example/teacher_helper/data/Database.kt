package com.example.teacher_helper.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teacher_helper.data.dao.StudentDao
import com.example.teacher_helper.data.dao.StudentsSubjectsDao
import com.example.teacher_helper.data.dao.SubjectDao
import com.example.teacher_helper.data.entities.Student
import com.example.teacher_helper.data.entities.StudentsSubjects
import com.example.teacher_helper.data.entities.Subject

@Database(entities = [Student::class, Subject::class, StudentsSubjects::class], version = 1, exportSchema = false)
abstract class MyDatabase: RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun subjectDao(): SubjectDao
    abstract fun studentsSubjectsDao(): StudentsSubjectsDao

    companion object{
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}