package com.example.t_rec.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t_rec.R
import com.example.t_rec.activity.QuestionActivity


class QuestionAdapter(private val questionList: List<QuestionActivity>) :
    RecyclerView.Adapter<QuestionAdapter.QuestionVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return QuestionVH(view)
    }

    override fun onBindViewHolder(holder: QuestionVH, position: Int) {
        val question = questionList[position]
        holder.questionTxt.text = question.getQuestion()
        holder.answerTxt.text = question.getAnswer()

        val isExpandableLayout = question.isExpandable()
        holder.expandableLayout.visibility =
            if (isExpandableLayout) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener {
            question.setCustomExpandable(!question.isExpandable())
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    inner class QuestionVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionTxt: TextView = itemView.findViewById(R.id.question)
        val answerTxt: TextView = itemView.findViewById(R.id.answer)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.linear_layout_faq)
        val expandableLayout: RelativeLayout = itemView.findViewById(R.id.expandable_layout)

        init {
            linearLayout.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val question = questionList[position]
                    question.setCustomExpandable(!question.isExpandable())
                    notifyItemChanged(position)
                }
            }
        }
    }
}