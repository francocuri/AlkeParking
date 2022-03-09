package domain

import java.util.Calendar

data class Parking(val vehicles: MutableSet<Vehicle>){
    val limit = 20

    fun addVehicle(vehicle: Vehicle) : Boolean {
        if(vehicles.size <= limit) {
            this.vehicles.add(vehicle)
            return true
        }
        return false
    }
}