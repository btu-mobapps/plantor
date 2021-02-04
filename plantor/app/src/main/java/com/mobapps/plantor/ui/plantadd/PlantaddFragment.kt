package com.mobapps.plantor.ui.plantadd

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mobapps.plantor.R
import com.mobapps.plantor.data.Plant
import kotlin.math.log

class PlantaddFragment : Fragment() {

    val REQUEST_CODE = 100

    private var selectedImageUri: Uri? = null

    private lateinit var notificationsViewModel: PlantaddViewModel
    private lateinit var select_imageView: ImageView
    private lateinit var addplant_btn: Button

    private lateinit var weekday_array: MutableList<CheckBox>
    private lateinit var plantname_editText: EditText
    private lateinit var waterhour_editText: EditText


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(PlantaddViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_plantadd, container, false)

        select_imageView = root.findViewById(R.id.select_plant_image_view)
        addplant_btn = root.findViewById(R.id.add_plant_btn)

        plantname_editText = root.findViewById(R.id.plant_name_input)
        waterhour_editText = root.findViewById(R.id.plant_water_hour)

        weekday_array = mutableListOf<CheckBox>()

        weekday_array.add(root.findViewById(R.id.day_monday_cb))
        weekday_array.add(root.findViewById(R.id.day_tuesday_cb))
        weekday_array.add(root.findViewById(R.id.day_wednesday_cb))
        weekday_array.add(root.findViewById(R.id.day_thursday_cb))
        weekday_array.add(root.findViewById(R.id.day_friday_cb))
        weekday_array.add(root.findViewById(R.id.day_saturday_cb))
        weekday_array.add(root.findViewById(R.id.day_sunday_cb))

        select_imageView.setOnClickListener {
            selectImageFromGallery()
        }

        addplant_btn.setOnClickListener {
            addPlant()
        }

        return root
    }

    fun addPlant () {
        var newPlant = Plant()

        var week_bitwise = 0

        for (i in 0..6) {
            if (weekday_array[i].isChecked) {
                week_bitwise = week_bitwise or (1 shl i)
            }
        }

        newPlant.imgUri = selectedImageUri.toString()

        newPlant.waterDays = week_bitwise
        newPlant.lastWaterDate = "never"

        newPlant.name = plantname_editText.text.toString()
        newPlant.waterHour = waterhour_editText.text.toString()

//      Log.d("CUSTOM", week_bitwise.toString(2).padStart(7, '0'));
        Log.d("CUSTOM", newPlant.toString());
    }

    private fun selectImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"

        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            selectedImageUri = data?.data

            select_imageView.setImageURI(selectedImageUri) // handle chosen image
        }
    }
}