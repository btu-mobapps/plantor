package com.mobapps.plantor.data

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.HashMap

public class FirebaseDatabaseHelper  {

    companion object {
        private var INSTANCE: FirebaseDatabaseHelper? = null

        public fun getInstance(): FirebaseDatabaseHelper? {
            if (INSTANCE == null) {
                INSTANCE = FirebaseDatabaseHelper()
            }

            return INSTANCE
        }
    }

    private var mDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var mReferencePlants: DatabaseReference
    private var mStorage: FirebaseStorage
    private var plants: List<Plant> = listOf()
    private var plant_refs: MutableMap<Plant, String> = mutableMapOf()

    constructor() {
        mReferencePlants = mDatabase.getReference("plants")
        mStorage = Firebase.storage
    }

    public fun addPlant (plantToAdd: Plant, onSuccess: () -> Unit) {
        mReferencePlants.child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child(plantToAdd.hashCode().toString()).setValue(plantToAdd).addOnSuccessListener {
            onSuccess()
        }
    }

    public fun deletePlantFromDatabase (plant: Plant, onSuccess: () -> Unit) {
        var key_ref = plant_refs[plant]

        if (key_ref != null) {
            mReferencePlants.child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child(key_ref).setValue(null).addOnSuccessListener {
                PlantDataManager.getInstance()!!.fetchDatabaseForPlants { onSuccess() }
            }
        }
    }

    public fun uploadImage (fromView: ImageView, onSuccess: (url: String) -> Unit) {
        val storageRef = mStorage.reference

        val uid = fromView.drawable.hashCode().toString()

        val pictureRef = storageRef.child(uid)

        val bitmap = (fromView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = pictureRef.putBytes(data)

        uploadTask.addOnFailureListener {}.addOnSuccessListener {}.addOnCompleteListener{
                task ->
            if (task.isSuccessful) {
                pictureRef.downloadUrl.addOnSuccessListener {
                    onSuccess(it.toString())
                }
            }
        }
    }

    public fun getPlantList (onSuccess: (plants: List<Plant>) -> Unit) {
        var snapshot = mReferencePlants.child(FirebaseAuth.getInstance().currentUser?.uid.toString()).get()

        snapshot.addOnSuccessListener {
            var user_plants = it.value

            if (user_plants == null) {
                Log.d("CUSTOM", "Database is empty!")
            } else {
                var plants = mutableListOf<Plant>()
                var plantlist_map = user_plants as HashMap<String, Any>

                for ((key, value) in plantlist_map) {
                    var plant_map = value as HashMap<String, Any>

                    var newPlant = Plant()

                    newPlant.name = plant_map["name"] as String
                    newPlant.imgUri = plant_map["imgUri"] as String
                    newPlant.lastWaterDate = plant_map["lastWaterDate"] as String
                    newPlant.waterHour = plant_map["waterHour"] as String
                    newPlant.waterDays = plant_map["waterDays"] as String

                    plants.add(newPlant)
                    plant_refs[newPlant] = key
                }

                onSuccess(plants)

//              Log.d("CUSTOM", plantlist_map.get(plantlist_map.keys.first())?.count().toString())
            }
        }

        snapshot.addOnFailureListener {
            Log.d("CUSTOM", "Failed to get response from database!")
        }
    }

}