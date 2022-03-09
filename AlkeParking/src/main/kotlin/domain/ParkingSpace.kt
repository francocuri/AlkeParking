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
        if(vehicle.plate == plate){

            var hasDiscountCard: Boolean = false
                vehicle.discountCard?.let { hasDiscountCard = true }

            val amountToPay = calculateFee(vehicle.type, parkedTime.toInt(),hasDiscountCard)
            removeVehicle(vehicle)
            onSuccess(amountToPay)
        }
        else {
            onError()
        }
    }

    fun calculateFee(type: VehicleType, parkedTime: Int, hasDiscountCard: Boolean) : Int {
        var amountToPay = type.price

        if (parkedTime > 120){
            var extraTime = parkedTime - 120
            // We use ceil because it always round up and we consider the extra amount of the last minutes
            var auxAmount = ceil(extraTime.toDouble() / 15)

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
        parking.history.first.inc()
        parking.history.second.plus(amountToPay)
        println(parking.history)
    }

    fun onError(){
        println("Sorry, the check-out failed.")
    }

    fun removeVehicle(vehicle: Vehicle){
        parking.vehicles.remove(vehicle)
    }
}
