package com.systemtron.neki.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.systemtron.neki.R
import com.systemtron.neki.utils.Constants
import com.systemtron.neki.utils.Tags

class DonateFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatedView = inflater.inflate(R.layout.fragment_donate, container, false)
        val sharedPreferences = requireContext().getSharedPreferences(
            Constants.welcomeTagSS,
            Context.MODE_PRIVATE
        )
        val welcomeInt = sharedPreferences?.getInt(Constants.sharedPreferencesWelcome, -1)
        Log.d(Tags.ishaanTag, "Welcome Int: $welcomeInt")
        return inflatedView
    }
}