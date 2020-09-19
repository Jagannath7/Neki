package com.systemtron.neki.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.systemtron.neki.R
import com.systemtron.neki.adapter.NGOAdapter
import com.systemtron.neki.modelClass.NGO
import com.systemtron.neki.utils.Constants
import com.systemtron.neki.utils.Tags
import kotlinx.android.synthetic.main.activity_list_of_ngo.*

class ListOfNgoActivity : AppCompatActivity() {

    private val currentUser by lazy {
        Firebase.auth.currentUser
    }

    private val db by lazy {
        Firebase.firestore
    }

    private lateinit var noteListener: ListenerRegistration

    private val listOfNGOs = ArrayList<NGO>()

    var catName = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_ngo)

        catName = intent.getStringExtra(Constants.keyCatName)!!
        tvHeading.text = "Showing NGOs who deal with $catName!"
    }

    override fun onStart() {
        super.onStart()
        noteListener = db.collection("ngos")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }

                for (snapshot in value!!) {
                    val ngo = snapshot.toObject(NGO::class.java)
                    ngo.emailId = snapshot.id
                    ngo.listCategory = ngo.categories.split(",")
                    Log.d(Tags.ishaanTag, "${ngo.emailId} ${ngo.listCategory}")
                    listOfNGOs.add(ngo)
                }
                val refinedList = ArrayList<NGO>()
                for (ngo in listOfNGOs) {
                    if (ngo.listCategory.contains(catName)) {
                        refinedList.add(ngo)
                    }
                }

                rvNGOList.apply {
                    layoutManager =
                        LinearLayoutManager(
                            this@ListOfNgoActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    adapter = NGOAdapter(refinedList, this@ListOfNgoActivity)
                }
            }


    }

    override fun onStop() {
        noteListener.remove()
        super.onStop()
    }
}