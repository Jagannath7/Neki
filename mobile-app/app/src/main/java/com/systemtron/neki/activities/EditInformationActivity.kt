package com.systemtron.neki.activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.systemtron.neki.R
import com.systemtron.neki.modelClass.User
import com.systemtron.neki.utils.Tags
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_edit_information.*
import kotlinx.android.synthetic.main.activity_edit_information.btnUpload
import kotlinx.android.synthetic.main.activity_edit_information.etCity
import kotlinx.android.synthetic.main.activity_edit_information.etLandmark
import kotlinx.android.synthetic.main.activity_edit_information.etName
import kotlinx.android.synthetic.main.activity_edit_information.etPhoneNumber
import kotlinx.android.synthetic.main.activity_edit_information.etPinCode
import kotlinx.android.synthetic.main.activity_edit_information.etState
import kotlinx.android.synthetic.main.activity_edit_information.etStreetAddress
import kotlinx.android.synthetic.main.activity_edit_information.ivPic
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditInformationActivity : AppCompatActivity() {

    private val currentUser by lazy {
        Firebase.auth.currentUser
    }

    private val db by lazy {
        Firebase.firestore
    }

    private val storage by lazy {
        Firebase.storage
    }
    private val ref by lazy {
        storage.reference.child("users")
    }

    private var url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_information)
        etName.editText?.text = intent.getStringExtra("fullName")!!.toEditable()
        etPhoneNumber.editText?.text = intent.getStringExtra("phoneNumber")!!.toEditable()
        etStreetAddress.editText?.text = intent.getStringExtra("streetAddress")!!.toEditable()
        etLandmark.editText?.text = intent.getStringExtra("landmark")!!.toEditable()
        etState.editText?.text = intent.getStringExtra("state")!!.toEditable()
        etCity.editText?.text = intent.getStringExtra("city")!!.toEditable()
        etPinCode.editText?.text = intent.getStringExtra("pincode")!!.toEditable()
        url = intent.getStringExtra("urlPP")!!
        Glide.with(this).load(intent.getStringExtra("urlPP")!!).into(ivPic)

        btnUpload.setOnClickListener {
            CropImage.activity().start(this)
        }

        btnSaveChanges.setOnClickListener {
            val fullName = etName.editText?.text.toString()
            var phoneNumber = etPhoneNumber.editText?.text.toString()
            if (phoneNumber.length == 10) {
                phoneNumber = "+91-$phoneNumber"
            }
            val streetAddress = etStreetAddress.editText?.text.toString()
            val landmark = etLandmark.editText?.text.toString()
            val city = etCity.editText?.text.toString()
            val state = etState.editText?.text.toString()
            val pincode = etPinCode.editText?.text.toString()
            val urlPP = url

            if (fullName.isEmpty() || phoneNumber.isEmpty() || streetAddress.isEmpty() || landmark.isEmpty() || city.isEmpty() || state.isEmpty() || pincode.isEmpty()) {
                Toast.makeText(this, "Please Fill All the Details", Toast.LENGTH_LONG).show()
            } else {
                val user = User(
                    fullName,
                    phoneNumber,
                    streetAddress,
                    landmark,
                    state,
                    city,
                    pincode,
                    urlPP
                )

                Log.d(Tags.ishaanTag, "User class formed: ${user.toString()}")

                GlobalScope.launch {
                    addToFireStore(user)
                }
            }
        }

    }

    private suspend fun addToFireStore(user: User) {
        db.collection("users")
            .document(currentUser!!.email.toString())
            .set(user)
            .addOnSuccessListener {
                Log.d(Tags.ishaanTag, "Info Updated")
                Log.d(Tags.ishaanTag, "edit info  -> Home")
                Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show()

                Handler().postDelayed({
                    val homeIntent = Intent(this, HomeActivity::class.java)
                    startActivity(homeIntent)
                }, 1000)

            }.addOnFailureListener {
                Log.d(Tags.ishaanTag, "Info changes failed ${it.toString()}")
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)

            if (resultCode == Activity.RESULT_OK) {
                val imageURI = result.uri
                Log.d("PUI", "$imageURI")
                uploadToStorage(imageURI, ref)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Error : ${result.error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun uploadToStorage(newFile: Uri, ref: StorageReference) {
        val imageRef = ref.child("${currentUser!!.email.toString()}/${newFile.lastPathSegment}")
        val uploadTask = imageRef.putFile(newFile)

        uploadTask.addOnSuccessListener {
            Log.d(Tags.ishaanTag, "Image Uploaded")
            imageRef.downloadUrl.addOnSuccessListener {
                Log.d(Tags.ishaanTag, "URL: $it")
                runOnUiThread {
                    url = it.toString()
                    btnUpload.visibility = View.GONE
                    ivPic.visibility = View.VISIBLE
                    Glide.with(this).load(it).into(ivPic)
                }
            }
        }.addOnFailureListener {
            Log.d("PUI", "Upload failed ${it.toString()}")
        }
    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}