package com.systemtron.neki.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.systemtron.neki.R
import com.systemtron.neki.modelClass.Transaction
import com.systemtron.neki.utils.Constants
import com.systemtron.neki.utils.Tags
import kotlinx.android.synthetic.main.activity_donation.*
import java.text.SimpleDateFormat
import java.util.*

class DonationActivity : AppCompatActivity() {
    private var fromName = ""
    private var fromPhone = ""
    private var fromSA = ""
    private var fromLand = ""
    private var fromPincode = ""

    private val auth by lazy {
        Firebase.auth
    }
    private val currentUser by lazy {
        auth.currentUser
    }

    private val db by lazy {
        Firebase.firestore
    }


    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation)
        getFromDetails()

        val name = intent.getStringExtra(Constants.keyFName)!!
        val streetAddress = intent.getStringExtra(Constants.keySA)!!
        val landmark = intent.getStringExtra(Constants.keyLand)!!
        val state = intent.getStringExtra(Constants.keyState)!!
        val city = intent.getStringExtra(Constants.keyCity)!!
        val pincode = intent.getStringExtra(Constants.keyPC)!!
        val email = intent.getStringExtra(Constants.keyEmail)!!
        val phoneNumber = intent.getStringExtra(Constants.keyPhone)!!

        tvName.text = name
        tvStreetAddress.text = streetAddress
        tvLandmark.text = landmark
        tvState.text = state
        tvCity.text = city
        tvPincode.text = pincode
        tvEmail.text = email
        tvPhone.text = phoneNumber

        btnDonate.setOnClickListener {
            val description = etDesc.editText?.text.toString()
            val id = "DON${System.currentTimeMillis() % 10000000}"
            val current = System.currentTimeMillis()
            val sdf = SimpleDateFormat("MMM dd,yyyy")
            val dateInstance = Date(current)
            val date = sdf.format(dateInstance)
            val transaction = Transaction(
                description,
                name,
                email,
                fromName,
                currentUser!!.email.toString(),
                fromPhone,
                fromSA,
                fromLand,
                fromPincode,
                date,
                "NIL"
            )

            db.collection("transactions")
                .document(id)
                .set(transaction)
                .addOnSuccessListener {
                    Log.d(Tags.ishaanTag, "Request Placed")
                    Toast.makeText(this, "Request Placed", Toast.LENGTH_SHORT).show()

                    Handler().postDelayed({
                        val returnIntent = Intent(this, HomeActivity::class.java)
                        startActivity(returnIntent)
                    }, 1000)
                }
                .addOnFailureListener {
                    Log.d(Tags.ishaanTag, "Request Failed, ${it.toString()}")
                    Toast.makeText(
                        this,
                        "Request Failed, Please Try Again Later",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    private fun getFromDetails() {
        db.collection("users")
            .document(currentUser!!.email.toString())
            .get()
            .addOnSuccessListener {
                if (it.exists()) {
                    fromName = it.getString("fullName").toString()
                    fromPhone = it.getString("phoneNumber").toString()
                    fromSA = it.getString("streetAddress").toString()
                    fromLand = it.getString("landmark").toString()
                    fromPincode = it.getString("pincode").toString()
                }
            }.addOnFailureListener {
                Log.d(Tags.ishaanTag, "Name Failed, ${it.toString()}")
            }
    }
}