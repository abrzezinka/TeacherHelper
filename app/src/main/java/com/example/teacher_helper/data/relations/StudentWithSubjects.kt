package com.example.teacher_helper.data.relations

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.teacher_helper.data.entities.Student
import com.example.teacher_helper.data.entities.StudentsSubjects
import com.example.teacher_helper.data.entities.Subject
import kotlinx.android.parcel.Parcelize


@Parcelize
data class StudentWithSubjects(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "subjectId",
        associateBy = Junction(StudentsSubjects::class)
    )
    val subjects: List<Subject>
): Parcelable