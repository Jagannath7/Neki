package com.systemtron.neki.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.systemtron.neki.R
import com.systemtron.neki.adapter.CategoryAdapter
import com.systemtron.neki.utils.Constants
import com.systemtron.neki.utils.Tags
import kotlinx.android.synthetic.main.fragment_donate.view.*

class DonateFragment : Fragment() {

    private val currentUser by lazy {
        Firebase.auth.currentUser
    }

    private val db by lazy {
        Firebase.firestore
    }

    private var name: String = ""

    private val arrayOfCategory = arrayListOf(
        "Clothing",
        "Electronic Gadgets",
        "Essentials",
        "Food",
        "Furniture",
        "Machinery",
        "Medical Equipments",
        "Monetary Funds",
        "Sports Equipments",
        "Stationary",
        "Toys"
    )

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatedView = inflater.inflate(R.layout.fragment_donate, container, false)
        val sharedPreferences = requireContext().getSharedPreferences(
            Constants.welcomeTagSS,
            Context.MODE_PRIVATE
        )

        val welcomeInt = sharedPreferences?.getInt(Constants.sharedPreferencesWelcome, -1)
        Log.d(Tags.ishaanTag, "Welcome Int: $welcomeInt")
        getNameFromFirestore()

        Log.d(Tags.ishaanTag, "name from getName: $name")
        Handler().postDelayed({
            if (welcomeInt == 0) {
                inflatedView.tvWelcomeOrHello.text = "Hello, $name!"
            } else if (welcomeInt == 1) {
                inflatedView.tvWelcomeOrHello.text = "Welcome Back, $name!"
            }
        }, 2000)

        val colorArray = arrayOf(
            Color.argb(255, 244, 67, 45),
            Color.argb(255, 63, 81, 181),
            Color.argb(255, 33, 150, 243),
            Color.argb(255, 156, 39, 176),
            Color.argb(255, 76, 175, 80),
            Color.argb(255, 255, 193, 7),
            Color.argb(255, 121, 85, 72),
            Color.argb(255, 255, 152, 0),
            Color.argb(255, 139, 195, 74),
            Color.argb(255, 0, 188, 212),
            Color.argb(255, 233, 67, 45),
        )
        inflatedView.rvCategories.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, LinearLayoutManager.HORIZONTAL, false)
            adapter = CategoryAdapter(arrayOfCategory, colorArray, requireContext())
        }

        return inflatedView
    }

    private fun getNameFromFirestore() {
        db.collection("users")
            .document(currentUser!!.email.toString())
            .get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val receivedName = it.getString(Constants.keyName).toString()
                    val nameList = receivedName.split(" ")
                    Log.d(Tags.ishaanTag, "${nameList[0]} $receivedName")
                    name = nameList[0]
                }
            }.addOnFailureListener {

            }
    }
}