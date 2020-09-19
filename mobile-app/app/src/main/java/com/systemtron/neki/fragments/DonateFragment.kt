package com.systemtron.neki.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.systemtron.neki.R
import com.systemtron.neki.utils.Constants
import com.systemtron.neki.utils.Tags
import kotlinx.android.synthetic.main.fragment_donate.*
import kotlinx.android.synthetic.main.fragment_donate.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DonateFragment : Fragment() {

    private val currentUser by lazy {
        Firebase.auth.currentUser
    }

    private val db by lazy {
        Firebase.firestore
    }

    private var name: String = ""

    val arrayOfCategory = arrayListOf(
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
        Handler().postDelayed({
            if (welcomeInt == 0) {
                inflatedView.tvWelcomeOrHello.text = "Hello, $name!"
            } else if (welcomeInt == 1) {
                inflatedView.tvWelcomeOrHello.text = "Welcome Back, $name!"
            }
        }, 1000)

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