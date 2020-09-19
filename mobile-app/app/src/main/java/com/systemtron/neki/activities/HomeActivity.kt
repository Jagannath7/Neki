package com.systemtron.neki.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.systemtron.neki.R
import com.systemtron.neki.fragments.DonateFragment
import com.systemtron.neki.fragments.ProfileFragment
import com.systemtron.neki.fragments.SearchFragment
import com.systemtron.neki.utils.Tags
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, DonateFragment())
            .commit()
        bottomNavigationBar.selectedItemId = R.id.menuDonate
        bottomNavigationBar.setOnNavigationItemSelectedListener(navigationItemChangeListener)
    }

    private val navigationItemChangeListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menuDonate -> {
                    Log.d(Tags.ishaanTag, "Going to Donate Fragment")
                    supportFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, DonateFragment())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menuProfile -> {
                    Log.d(Tags.ishaanTag, "Going to Profile Fragment")
                    supportFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, ProfileFragment())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menuSearch -> {
                    Log.d(Tags.ishaanTag, "Going to Menu Fragment")
                    supportFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, SearchFragment())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onBackPressed() {
        Log.d(Tags.ishaanTag, "Finishing App")
        finishAffinity()
    }
}