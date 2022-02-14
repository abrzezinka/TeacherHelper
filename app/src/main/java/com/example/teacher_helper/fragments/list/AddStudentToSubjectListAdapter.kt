package com.example.teacher_helper.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teacher_helper.R
import com.example.teacher_helper.data.entities.Student
import com.example.teacher_helper.data.entities.StudentsSubjects
import com.example.teacher_helper.data.entities.Subject

class AddStudentToSubjectListAdapter(val toRelation: (id:Long) -> Unit):
    RecyclerView.Adapter<AddStudentToSubjectListAdapter.MyViewHolder>() {

    private var studentsList = emptyList<Student>()
    private var subject = StudentsSubjects(1L,1L)
    var subjectId: Long = 1L

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val name: TextView
        val lastName: TextView
        val number: TextView
        val addStudent: Button

        init {
            name = itemView.findViewById<TextView>(R.id.studentName_txt)
            lastName = itemView.findViewById<TextView>(R.id.studentLastName_txt)
            number = itemView.findViewById<TextView>(R.id.studentNumber_txt)
            addStudent = itemView.findViewById<Button>(R.id.addStudentToSubject_btn)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.add_student_to_subject_row, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = studentsList[position]
        holder.name.text = currentItem.firstName
        holder.lastName.text = currentItem.lastName
        holder.number.text = currentItem.number.toString()
        holder.addStudent.setOnClickListener(){
            currentItem.studentId.let { it1 -> toRelation(it1) }
        }

    }

    override fun getItemCount(): Int {
        return studentsList.size
    }

    fun setData(students: List<Student>){
        this.studentsList = students
        notifyDataSetChanged()
    }
}