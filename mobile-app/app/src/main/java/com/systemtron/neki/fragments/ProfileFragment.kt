package com.systemtron.neki.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.systemtron.neki.R
import com.systemtron.neki.activities.LoginActivity
import com.systemtron.neki.adapter.TransactionAdapter
import com.systemtron.neki.modelClass.History
import com.systemtron.neki.utils.Constants
import com.systemtron.neki.utils.Tags
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    private val currentUser by lazy {
        Firebase.auth.currentUser
    }

    private val sharedPreferences by lazy {
        requireContext().getSharedPreferences(Constants.welcomeTagSS, Context.MODE_PRIVATE)
    }

    private val db by lazy {
        Firebase.firestore
    }

    private val listOfTransaction = arrayListOf<History>()
    private var adapter = TransactionAdapter(listOfTransaction)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflatedView = inflater.inflate(R.layout.fragment_profile, container, false)
        val name = sharedPreferences?.getString(Constants.userName, "")
        val url = sharedPreferences?.getString(Constants.userPP, "")
        Log.d(Tags.ishaanTag, "$name $url")

        addTransaction(inflatedView)

        inflatedView.tvName.text = name!!
        Glide.with(this@ProfileFragment).load(url).into(inflatedView.ivProfilePic)

        inflatedView.btnEdit.setOnClickListener {
            Toast.makeText(requireContext(), "In Progress", Toast.LENGTH_SHORT).show()
        }

        inflatedView.btnSignOut.setOnClickListener {
            Firebase.auth.signOut()
            val returnIntent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(returnIntent)
        }
        return inflatedView
    }

    private fun addTransaction(inflatedView: View) {
        db.collection("transactions")
            .whereEqualTo("fromEmail", currentUser!!.email.toString())
            .orderBy("date", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                for (value in it) {
                    val history = value.toObject(History::class.java)
                    listOfTransaction.add(history)
                }
                Log.d(Tags.ishaanTag, "$listOfTransaction")
                inflatedView.rvTransactions.apply {
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    adapter = this@ProfileFragment.adapter
                }
            }
            .addOnFailureListener {
                Log.d(Tags.ishaanTag, "${it.toString()}")
            }
    }
}