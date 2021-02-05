package com.mobapps.plantor.data

class PlantDataManager {

    private var plantList: ArrayList<Plant> = arrayListOf()

    private var firstFetchDone: Boolean = false

    companion object {
        private var INSTANCE: PlantDataManager? = null

        public fun getInstance(): PlantDataManager? {
            if (INSTANCE == null) {
                INSTANCE = PlantDataManager()
            }

            return INSTANCE
        }
    }

    fun init () {

    }

    fun resetManager () {
        firstFetchDone = false

        plantList.clear()
    }

    fun fetchDatabaseForPlants (onComplete: () -> Unit) {
        FirebaseDatabaseHelper.getInstance()?.getPlantList {
            plants ->
            run {
                plantList.clear()

                for (plant in plants) {
                    plantList.add(plant)
                }

                onComplete()
            }
        }
    }

    fun getPlants (onComplete: () -> Unit): List<Plant> {
        if (!firstFetchDone) {
            fetchDatabaseForPlants(onComplete)

            firstFetchDone = true
        }

        return plantList
    }


}