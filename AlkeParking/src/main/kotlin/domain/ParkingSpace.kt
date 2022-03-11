package domain

import java.util.*
import kotlin.math.ceil

data class ParkingSpace(var vehicle: Vehicle, val parking: Parking){

    val parkedTime: Long
        get() {
            val MINUTES_IN_MILISECOND = 60000
            val cal = Calendar.getInstance()
            cal.set(2022, 2, 9, 21  , 13)
            //return (Calendar.getInstance().timeInMillis - vehicle.checkInTime.timeInMillis) / MINUTES_IN_MILISECOND
            return (cal.timeInMillis - vehicle.checkInTime.timeInMillis)/ MINUTES_IN_MILISECOND
        }

    fun checkOutVehicle(plate: String) {
        if(parking.isInParking(vehicle) && vehicle.plate == plate){

            var hasDiscountCard = false
                vehicle.discountCard?.let { hasDiscountCard = true }

            val amountToPay = calculateFee(vehicle.type,hasDiscountCard)
            removeVehicle(vehicle)
            onSuccess(amountToPay)
        }
        else {
            onError()
        }
    }

    // We removed parkedTime parameter because we already have a local variable with this value
    fun calculateFee(type: VehicleType, hasDiscountCard: Boolean) : Int {
        var amountToPay = type.price

        if (parkedTime > 120){
            val extraTime = parkedTime - 120
            // We use ceil because it always round up and we consider the extra amount of the last minutes
            val auxAmount = ceil(extraTime.toDouble() / 15)

            val extraAmount = auxAmount * 5
            amountToPay += extraAmount.toInt()

        }

        if (hasDiscountCard) {
            // We use toInt() because the fractional part, if any, is always rounded down towards zero.
            // See more at https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/to-int.html
            amountToPay -= (amountToPay * 0.15).toInt()
        }

        return amountToPay
    }

    fun onSuccess(amountToPay: Int){
        println("Your fee is $$amountToPay. Come back soon.")
        // We create a new instance of Pair because it is immutable
        val newHistory = Pair(parking.history.first + 1, parking.history.second + amountToPay)
        parking.history = newHistory
    }

    fun onError(){
        println("Sorry, the check-out failed.")
    }

    fun removeVehicle(vehicle: Vehicle){
        // We delegate this responsibility to Parking
        parking.removeVehicle(vehicle)
    }
}
