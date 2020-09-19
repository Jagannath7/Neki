package com.systemtron.neki.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.systemtron.neki.R
import com.systemtron.neki.utils.Constants
import com.systemtron.neki.utils.Tags
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val auth by lazy {
        Firebase.auth
    }

    private val currentUser by lazy {
        auth.currentUser
    }

    private val sharedPreferences by lazy {
        getSharedPreferences(Constants.welcomeTagSS, Context.MODE_PRIVATE)
    }

    private val editor by lazy {
        sharedPreferences.edit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (currentUser != null) {
            editor.putInt(Constants.sharedPreferencesWelcome, 1)
            editor.apply()
            val signUpIntent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(signUpIntent)
        }

        btnLogin.setOnClickListener {
            val email = etEmailId.editText?.text.toString()
            val password = etPassword.editText?.text.toString()

            if (email.isEmpty() or password.isEmpty()) {
                Toast.makeText(this, "Please Enter Email Id or Password", Toast.LENGTH_SHORT).show()
            } else {
                checkEmail(email, password)
            }
        }
    }

    private fun checkEmail(email: String, password: String) {
        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener {
                if (it.result?.signInMethods?.isEmpty()!!) {
                    Log.d(Tags.ishaanTag, "Sign Up at fetchSignInMethods")
                    signUp(email, password)
                } else {
                    Log.d(Tags.ishaanTag, "Sign In at fetchSignInMethods")
                    signIn(email, password)
                }
            }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    try {
                        throw it.exception!!
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthInvalidUserException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthUserCollisionException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }.addOnSuccessListener {
                Toast.makeText(this, "Sign In Successful", Toast.LENGTH_SHORT).show()
                Log.d(Tags.ishaanTag, "Sign in Successful")
                Log.d(Tags.ishaanTag, "Login -> Home")

                Handler().postDelayed({
                    val signInIntent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(signInIntent)
                }, 1000)
            }.addOnFailureListener {
                Toast.makeText(this, "Sign In Failed", Toast.LENGTH_SHORT).show()
                Log.d(Tags.ishaanTag, "Sign in Failed")
            }
    }

    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    try {
                        throw it.exception!!
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthInvalidUserException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthUserCollisionException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }.addOnSuccessListener {
                Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                Log.d(Tags.ishaanTag, "Sign up Successful")
                Log.d(Tags.ishaanTag, "Login -> Signup")

                editor.putInt(Constants.sharedPreferencesWelcome, 1)
                editor.apply()

                Handler().postDelayed({
                    val signUpIntent = Intent(this@LoginActivity, SignUpActivity::class.java)
                    startActivity(signUpIntent)
                }, 1000)
            }.addOnFailureListener {
                Toast.makeText(this, "Sign Up Failed", Toast.LENGTH_SHORT).show()
                Log.d(Tags.ishaanTag, "Sign up Failed")
            }
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}