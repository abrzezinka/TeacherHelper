package com.example.teacher_helper.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.teacher_helper.R
import com.example.teacher_helper.data.entities.StudentsSubjects
import com.example.teacher_helper.data.entities.Subject

class AddSubjectToStudentListAdapter(val toRelation: (id:Long) -> Unit):
    RecyclerView.Adapter<AddSubjectToStudentListAdapter.MyViewHolder>() {

    private var subjectsList = emptyList<Subject>()
    private var student = StudentsSubjects(1L,1L)
    var studentId: Long = 1L

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val addSubject: Button
        val rowLayout: ConstraintLayout

        init {
            name = itemView.findViewById<TextView>(R.id.subjectName_txt)
            addSubject = itemView.findViewById<Button>(R.id.addSubjectToStudent_btn)
            rowLayout = itemView.findViewById<ConstraintLayout>(R.id.addSubjectToStudent_rowLayout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.add_subject_to_student_row, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = subjectsList[position]
        holder.name.text = currentItem.name
        student = StudentsSubjects(studentId,position.toLong())
        holder.addSubject.setOnClickListener(){
            currentItem.subjectId.let { it1 -> toRelation(it1) }
        }
    }

    fun addRelationToDataBase(): StudentsSubjects {
        return student
    }

    override fun getItemCount(): Int {
        return subjectsList.size
    }

    fun setData(subjects: List<Subject>){
        this.subjectsList = subjects
        notifyDataSetChanged()
    }
}