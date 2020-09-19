package com.systemtron.neki.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.systemtron.neki.R
import com.systemtron.neki.utils.Constants
import kotlinx.android.synthetic.main.activity_donation.*

class DonationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation)

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
    }
}