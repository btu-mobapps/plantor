package com.mobapps.plantor.data

data class Plant (
    var name: String = "",
    var imgUri: String = "",
    var lastWaterDate: String = "",
    var waterHour: String = "",
    var waterDays: Int = 0
)