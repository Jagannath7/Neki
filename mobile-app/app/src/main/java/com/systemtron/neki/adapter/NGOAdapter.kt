package com.systemtron.neki.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.systemtron.neki.R
import com.systemtron.neki.modelClass.NGO
import kotlinx.android.synthetic.main.cv_ngos.view.*

class NGOAdapter(private val listOfNGOs: ArrayList<NGO>, private val context: Context) :
    RecyclerView.Adapter<NGOAdapter.NGOViewHolder>() {

    class NGOViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(ngo: NGO) {
            with(itemView) {
                tvNGOName.text = ngo.name
                tvNGOTagline.text = ngo.tagline
                tvNGOCategory.text = ngo.categories

                Glide.with(context).load(ngo.iconUrl).into(ivNGOIcon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NGOViewHolder {
        return NGOViewHolder(LayoutInflater.from(context).inflate(R.layout.cv_ngos, parent, false))
    }

    override fun onBindViewHolder(holder: NGOViewHolder, position: Int) {
        holder.bind(listOfNGOs[position])
    }

    override fun getItemCount(): Int = listOfNGOs.size
}