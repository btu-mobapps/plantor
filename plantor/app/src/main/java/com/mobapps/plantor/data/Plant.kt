package com.mobapps.plantor.data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Plant(
    var name: String? = null,
    var imgUri: String? = null,
    var lastWaterDate: String? = null,
    var waterHour: String? = null,
    var waterDays: String? = null
)