package com.mobapps.plantor.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.mobapps.plantor.R
import com.mobapps.plantor.data.DateManager
import com.mobapps.plantor.data.FirebaseDatabaseHelper
import com.mobapps.plantor.ui.login.LoginActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var buttonLogout : Button
    private lateinit var userAuth : FirebaseAuth

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        buttonLogout = root.findViewById(R.id.home_logoutBtn)

        userAuth = FirebaseAuth.getInstance()

        buttonLogout.setOnClickListener{
            userAuth.signOut()

            startActivity(Intent(this.context, LoginActivity::class.java))

            activity?.finish()
        }

        root.findViewById<Button>(R.id.database_print).setOnClickListener {
//            FirebaseDatabaseHelper.getInstance()?.printPlant()


        }

        return root
    }
}