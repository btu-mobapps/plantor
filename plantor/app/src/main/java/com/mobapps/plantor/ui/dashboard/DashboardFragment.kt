package com.mobapps.plantor.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobapps.plantor.R
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

        val plantList = ArrayList<Plant>()

        plantList.add(Plant(imgUrl = "https://cdn.pixabay.com/photo/2016/11/14/04/45/elephant-1822636_960_720.jpg"))
        plantList.add(Plant(imgUrl = "https://cdn.pixabay.com/photo/2018/01/14/23/12/nature-3082832_960_720.jpg"))
        plantList.add(Plant(imgUrl = "https://cdn.pixabay.com/photo/2015/12/01/20/28/fall-1072821_960_720.jpg"))

        val adapter = PlantAdapter(plantList, root.context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(root.context, 2)

        return root
    }
}