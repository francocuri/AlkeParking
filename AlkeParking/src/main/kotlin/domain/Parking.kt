package domain

data class Parking(val vehicles: MutableSet<Vehicle>){
    private val limit = 20
    var history :Pair<Int,Int>  = Pair(0, 0)

    fun addVehicle(vehicle: Vehicle) : Boolean {
        return if (vehicles.size >= limit){
            false
        } else {
            this.vehicles.add(vehicle)
        }
    }

    fun removeVehicle(vehicle: Vehicle)  {
        if(vehicles.contains(vehicle)) {
            vehicles.remove(vehicle)
        }
    }

    fun checkoutHistory(){
        println("${history.first} vehicles have checked out and have earnings of $${history.second}")
    }

    fun listVehicles(){
        println("Vehicles plates currently parked: ")
        vehicles.forEach{
            println(it.plate)
        }
    }

    fun isInParking(vehicle: Vehicle) : Boolean{
        return vehicles.contains(vehicle)
    }
}