package com.systemtron.neki.activities

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.systemtron.neki.R
import com.systemtron.neki.modelClass.User
import com.systemtron.neki.utils.Tags
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {

    private val currentUser by lazy {
        Firebase.auth.currentUser
    }

    private val db by lazy {
        Firebase.firestore
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btnSubmit.setOnClickListener {
            val fullName = etName.editText?.text.toString()
            val phoneNumber = "+91-${etPhoneNumber.editText?.text.toString()}"
            val streetAddress = etStreetAddress.editText?.text.toString()
            val landmark = etLandmark.editText?.text.toString()
            val city = etCity.editText?.text.toString()
            val state = etState.editText?.text.toString()
            val pincode = etPinCode.editText?.text.toString()

            val user = User(
                fullName,
                phoneNumber,
                streetAddress,
                landmark,
                state,
                city,
                pincode
            )

            Log.d(Tags.ishaanTag, "User class formed: $user")

            GlobalScope.launch {
                addToFireStore(user)
            }
        }
    }

    private suspend fun addToFireStore(user: User) {
        db.collection("users")
            .document(currentUser!!.email.toString())
            .set(user)
            .addOnSuccessListener {
                Log.d(Tags.ishaanTag, "Added to user db")
                Log.d(Tags.ishaanTag, "Sign up -> Home")

                Handler().postDelayed({
                    val homeIntent = Intent(this, HomeActivity::class.java)
                    startActivity(homeIntent)
                }, 1000)

            }.addOnFailureListener {
                Log.d(Tags.ishaanTag, "User db failed ${it.toString()}")
            }
    }

    override fun onBackPressed() {
        Log.d(Tags.ishaanTag, "Sign up -> Login")
        val backIntent = Intent(this, LoginActivity::class.java)
        startActivity(backIntent)
    }
}