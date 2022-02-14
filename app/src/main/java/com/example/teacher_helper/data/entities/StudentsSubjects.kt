package com.example.teacher_helper.data.entities;

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = ["studentId", "subjectId"])
data class StudentsSubjects(
    val studentId: Long,
    val subjectId: Long
): Parcelable




