package com.systemtron.neki.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.systemtron.neki.R
import com.systemtron.neki.modelClass.History
import kotlinx.android.synthetic.main.cv_history.view.*

class TransactionAdapter(private val list: List<History>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(history: History) {
            with(itemView) {
                tvTo.text = history.toName
                tvDate.text = history.date
                tvDescription.text = history.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cv_history, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}