package com.systemtron.neki.fragments

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.systemtron.neki.R
import com.systemtron.neki.adapter.NGOAdapter
import com.systemtron.neki.adapter.TransactionAdapter
import com.systemtron.neki.modelClass.NGO
import com.systemtron.neki.utils.Tags
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment() {

    private val db by lazy {
        Firebase.firestore
    }

    private var listOfNGOs = ArrayList<NGO>()
    private lateinit var mAdapter: NGOAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflatedView = inflater.inflate(R.layout.fragment_search, container, false)
        inflatedView.rvSearch.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        mAdapter = NGOAdapter(listOfNGOs, requireContext())
        inflatedView.rvSearch.adapter = mAdapter

        getNamesOfNGO()

        inflatedView.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString(), inflatedView)
            }

        })

        return inflatedView
    }

    private fun filter(string: String, inflatedView: View) {
        val filtered = ArrayList<NGO>()

        for (value in listOfNGOs) {

            if (value.name.toLowerCase().contains(string.toLowerCase())) {
                //adding the element to filtered list
                filtered.add(value);
            }
        }
        Log.d(Tags.ishaanTag, "$filtered")
        mAdapter.filterList(filtered)
    }

    private fun getNamesOfNGO() {
        db.collection("ngos")
            .get()
            .addOnSuccessListener {
                for (snapshot in it) {
                    val ngo = snapshot.toObject(NGO::class.java)
                    ngo.emailId = snapshot.id
                    ngo.listCategory = ngo.categories.split(",")
                    listOfNGOs.add(ngo)
                }
            }
    }
}