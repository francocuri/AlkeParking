package domain

import java.util.Calendar

data class Parking(val vehicles: MutableSet<Vehicle>){
    val limit = 20
    var history :Pair<Int,Int>  = Pair(0, 0)

    fun addVehicle(vehicle: Vehicle) : Boolean {
        if(vehicles.size <= limit) {
            this.vehicles.add(vehicle)

            return true
        }
        return false
    }
}