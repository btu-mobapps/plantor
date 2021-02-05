package com.mobapps.plantor.ui.dashboard

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobapps.plantor.R
import com.mobapps.plantor.data.FirebaseDatabaseHelper
import com.mobapps.plantor.data.Plant

class PlantAdapter(private var plants:List<Plant>, private val context: Context): RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    lateinit var dashboardFrag: DashboardFragment

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val cancelImg: ImageView = itemView.findViewById(R.id.cancel_img)

        var plant_refs: MutableMap<ImageView, Plant> = mutableMapOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plant_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.plant_refs.clear()
        }

        val curPlant = plants[position]

        holder.plant_refs[holder.imageView] = curPlant

        Glide.with(context)
            .load(curPlant.imgUri)
            .placeholder(R.drawable.ic_launcher_background)
            .centerCrop()
            .into(holder.imageView)

        holder.imageView.setOnClickListener{
            Log.d("MSG_LK", "Water plant")
        }

        holder.cancelImg.setOnClickListener{
            holder.plant_refs[holder.imageView]?.let { plant ->
                FirebaseDatabaseHelper.getInstance()?.deletePlantFromDatabase(plant) { dashboardFrag.updateRecyclerView() }
            }

            Log.d("MSG_LK", "Remove Plant")
        }
    }

    override fun getItemCount(): Int  = plants.size

}