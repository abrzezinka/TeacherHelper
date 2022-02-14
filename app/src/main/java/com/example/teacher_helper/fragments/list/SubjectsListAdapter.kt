package com.example.teacher_helper.fragments.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacher_helper.R
import com.example.teacher_helper.data.entities.Subject

class SubjectsListAdapter: RecyclerView.Adapter<SubjectsListAdapter.MyViewHolder>() {

    private var subjectsList = emptyList<Subject>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val rowLayout: ConstraintLayout

        init {
            name = itemView.findViewById<TextView>(R.id.name_txt)
            rowLayout = itemView.findViewById<ConstraintLayout>(R.id.subjectRowLayout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.subjects_row, parent,
            false) as View)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = subjectsList[position]
        holder.name.text = currentItem.name
        holder.rowLayout.setOnClickListener{
            val action = SubjectsListDirections.
            actionSubjectsListToStudentsList(viewType = "fromSubject", position.toLong()+1)
            holder.itemView.findNavController().navigate(action)
        }
        holder.itemView.findViewById<ImageButton>(R.id.addStudent_btn).setOnClickListener{
            val action = SubjectsListDirections.actionSubjectsListToStudentsList(
                viewType = "addStudent", subjectId = currentItem.subjectId)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return subjectsList.size
    }

    fun setData(subjects: List<Subject>){
        this.subjectsList = subjects
        notifyDataSetChanged()
    }
}