package com.systemtron.neki.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.systemtron.neki.R
import com.systemtron.neki.activities.DonationActivity
import com.systemtron.neki.modelClass.NGO
import com.systemtron.neki.utils.Constants
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

                setOnClickListener {
                    val furtherIntent = Intent(context, DonationActivity::class.java)
                    furtherIntent.putExtra(Constants.keyFName, ngo.name)
                    furtherIntent.putExtra(Constants.keySA, ngo.streetAddress)
                    furtherIntent.putExtra(Constants.keyLand, ngo.landmark)
                    furtherIntent.putExtra(Constants.keyState, ngo.state)
                    furtherIntent.putExtra(Constants.keyCity, ngo.city)
                    furtherIntent.putExtra(Constants.keyPC, ngo.pincode)
                    furtherIntent.putExtra(Constants.keyEmail, ngo.emailId)
                    furtherIntent.putExtra(Constants.keyPhone, ngo.phoneNumber)
                    startActivity(context, furtherIntent, null)
                }
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