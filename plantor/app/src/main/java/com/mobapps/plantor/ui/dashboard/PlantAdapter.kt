package com.mobapps.plantor.ui.dashboard

import android.content.Context
import android.graphics.PorterDuff
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobapps.plantor.R
import com.mobapps.plantor.data.DateManager
import com.mobapps.plantor.data.FirebaseDatabaseHelper
import com.mobapps.plantor.data.Plant
import com.mobapps.plantor.data.PlantDataManager

class PlantAdapter(private var plants:List<Plant>, private val context: Context): RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    lateinit var dashboardFrag: DashboardFragment
    var plant_refs: MutableMap<ImageView, Plant> = mutableMapOf()
    var plant_uids: MutableMap<ImageView, String> = mutableMapOf()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val cancelImg: ImageView = itemView.findViewById(R.id.cancel_img)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plant_view, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            plant_refs.clear()
        }

        val curPlant = plants[position]

        plant_refs[holder.imageView] = curPlant

        if (DateManager.getInstance()?.compareBitwiseWithCurrent(curPlant.waterDays!!.toInt())!!) {
            Log.d("FB_DB", "Water Today!")

            if (curPlant.lastWaterDate == "never" || DateManager.getInstance()!!.wasLastWaterToday(curPlant.lastWaterDate!!)) {
                holder.imageView.setColorFilter(context.getColor(R.color.plantview_not_watered_tint), PorterDuff.Mode.MULTIPLY)

                holder.imageView.setOnClickListener{
                    plant_refs[holder.imageView]?.let { ref ->
                        var curDate = DateManager.getInstance()?.getCurrentDate()

//                Log.d("FB_DB", curDate.toString())

                        FirebaseDatabaseHelper.getInstance()?.updatePlant(ref, curDate.toString()) { dashboardFrag.updateRecyclerView() }
                    }

                    Log.d("FB_DB", "Plant Watered")
                }
            } else {
                holder.imageView.setColorFilter(context.getColor(R.color.plantview_watered_tint), PorterDuff.Mode.MULTIPLY)
            }
        } else {
            Log.d("FB_DB", "No need for Water!")

            holder.imageView.setColorFilter(context.getColor(R.color.plantview_no_need_tint), PorterDuff.Mode.MULTIPLY)
        }


        FirebaseDatabaseHelper.getInstance()?.onDatabaseEmpty?.plusAssign {
            PlantDataManager.getInstance()?.resetManager()
            dashboardFrag.updateRecyclerViewNoFetch()
        }

        Glide.with(context)
            .load(curPlant.imgUri)
            .placeholder(R.drawable.ic_launcher_background)
            .centerCrop()
            .into(holder.imageView)

        holder.cancelImg.setOnClickListener{
            plant_refs[holder.imageView]?.let { ref ->
                FirebaseDatabaseHelper.getInstance()?.deletePlantFromDatabase(ref) { dashboardFrag.updateRecyclerView() }
            }

            Log.d("MSG_LK", "Remove Plant")
        }
    }

    override fun getItemCount(): Int  = plants.size

}