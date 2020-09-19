package com.systemtron.neki.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.systemtron.neki.R

class SplashActivity : AppCompatActivity() {

    private val splashTimeOut = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val splashIntent = Intent(this, LoginActivity::class.java)
            startActivity(splashIntent)
        }, 3000)
    }
}