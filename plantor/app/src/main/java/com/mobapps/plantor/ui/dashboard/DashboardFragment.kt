package com.mobapps.plantor.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobapps.plantor.R
import com.mobapps.plantor.data.FirebaseDatabaseHelper
import com.mobapps.plantor.data.Plant
import com.mobapps.plantor.data.PlantDataManager

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var cxt: Context

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        cxt = root.context

        recyclerView = root.findViewById(R.id.recycler_view)

        PlantDataManager.getInstance()!!.getPlants{
            updateRecyclerView()
        }

        updateRecyclerView()

        return root
    }

    fun updateRecyclerView () {
        var plants = PlantDataManager.getInstance()!!.getPlants {  }
        val adapter = PlantAdapter(plants, cxt)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(cxt, 2)
    }
}