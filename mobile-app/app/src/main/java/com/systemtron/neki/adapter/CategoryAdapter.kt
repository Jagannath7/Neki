package com.systemtron.neki.adapter

import android.content.Context
import android.content.Intent
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.systemtron.neki.R
import com.systemtron.neki.activities.ListOfNgoActivity
import com.systemtron.neki.utils.Constants
import kotlinx.android.synthetic.main.cv_donate_category.view.*

class CategoryAdapter(
    private val arrayOfCategory: ArrayList<String>,
    private val colorArray: Array<Int>,
    val context: Context
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(name: String, color: Int) {
            with(itemView) {
                tvCatName.text = name
                cvCat.radius = 15.toDp(context).toFloat()
                cvCat.setCardBackgroundColor(color)

                setOnClickListener {
                    val furtherIntent = Intent(context, ListOfNgoActivity::class.java)
                    furtherIntent.putExtra(Constants.keyCatName, name)
                    startActivity(context, furtherIntent, null)
                }
            }
        }

        private fun Int.toDp(context: Context): Int = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics
        ).toInt()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cv_donate_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(arrayOfCategory[position], colorArray[position])
    }

    override fun getItemCount(): Int = arrayOfCategory.size
}