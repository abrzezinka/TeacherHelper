package com.example.teacher_helper.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacher_helper.R
import com.example.teacher_helper.data.entities.Student

class StudentsListAdapter: RecyclerView.Adapter<StudentsListAdapter.MyViewHolder>(){

    private var studentList = emptyList<Student>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textViewFirstName: TextView
        val textViewLastName: TextView
        val textViewNumber: TextView
        val rowLayout: ConstraintLayout

        init{
            textViewFirstName = itemView.findViewById<TextView>(R.id.firstName)
            textViewLastName = itemView.findViewById<TextView>(R.id.lastName)
            textViewNumber = itemView.findViewById<TextView>(R.id.number)
            rowLayout = itemView.findViewById<ConstraintLayout>(R.id.studentRowLayout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.students_row, parent,
            false) as View)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = studentList[position]
        holder.textViewFirstName.text = currentItem.firstName
        holder.textViewLastName.text = currentItem.lastName
        holder.textViewNumber.text = currentItem.number.toString()

        holder.rowLayout.setOnClickListener{
            val action = StudentsListDirections.actionStudentsListToSubjectsList(
                viewType = "fromStudent", position.toLong()+1)
            holder.itemView.findNavController().navigate(action)
        }
        holder.itemView.findViewById<ImageButton>(R.id.editStudent_btn).setOnClickListener{
            val action = StudentsListDirections.actionStudentsListToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.findViewById<ImageButton>(R.id.addSubjects_btn).setOnClickListener{
            val action = StudentsListDirections.actionStudentsListToSubjectsList(
                viewType = "addSubject", studentId = currentItem.studentId)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun setData(students: List<Student>){
        this.studentList = students
        notifyDataSetChanged()
    }


}