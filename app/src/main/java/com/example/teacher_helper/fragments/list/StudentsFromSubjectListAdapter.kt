package com.example.teacher_helper.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.teacher_helper.R
import com.example.teacher_helper.data.entities.Student


class StudentsFromSubjectListAdapter: RecyclerView.Adapter<StudentsFromSubjectListAdapter.MyViewHolder>(){

    private var studentList = emptyList<Student>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textViewFirstName: TextView
        val textViewLastName: TextView
        val textViewNumber: TextView
        //val rowLayout: ConstraintLayout

        init{
            textViewFirstName = itemView.findViewById<TextView>(R.id.nameOfStudFromSub_txt)
            textViewLastName = itemView.findViewById<TextView>(R.id.lastNameOfStudFromSub_txt)
            textViewNumber = itemView.findViewById<TextView>(R.id.numberOfStudFromSub_txt)
           // rowLayout = itemView.findViewById<ConstraintLayout>(R.id.studentRowLayout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.students_from_subject, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = studentList[position]
        holder.textViewFirstName.text = currentItem.firstName
        holder.textViewLastName.text = currentItem.lastName
        holder.textViewNumber.text = currentItem.number.toString()
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun setData(students: List<Student>){
        this.studentList = students
        notifyDataSetChanged()
    }

}