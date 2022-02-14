package com.example.teacher_helper.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacher_helper.R
import com.example.teacher_helper.data.entities.Student
import com.example.teacher_helper.data.entities.Subject

class SubjectsOfStudentListAdapter: RecyclerView.Adapter<SubjectsOfStudentListAdapter.MyViewHolder>() {


    private var subjectsList = emptyList<Subject>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView
        //val rowLayout: ConstraintLayout

        init {
            name = itemView.findViewById<TextView>(R.id.nameOfSubFromStud_txt)
           // rowLayout = itemView.findViewById<ConstraintLayout>(R.id.subjectRowLayout)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).
        inflate(R.layout.subjects_of_student, parent, false) as View)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = subjectsList[position]
        holder.name.text = currentItem.name
    }

    override fun getItemCount(): Int {
        return subjectsList.size
    }

    fun setData(subjects: List<Subject>){
        this.subjectsList = subjects
        notifyDataSetChanged()
    }

}