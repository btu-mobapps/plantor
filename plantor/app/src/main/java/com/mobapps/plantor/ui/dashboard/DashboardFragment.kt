package com.mobapps.plantor.ui.dashboard

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

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private  lateinit var recyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerView = root.findViewById(R.id.recycler_view)

        FirebaseDatabaseHelper.getInstance()?.getPlantList {
                plants ->
            run {
                val adapter = PlantAdapter(plants, root.context)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = GridLayoutManager(root.context, 2)
            }
        }

        return root
    }
}