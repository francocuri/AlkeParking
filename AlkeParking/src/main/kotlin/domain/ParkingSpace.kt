package domain

import java.util.Calendar

data class ParkingSpace(var vehicle: Vehicle, val parking: Parking){

    val parkedTime: Long
        get() {
            val MINUTES_IN_MILISECOND = 60000
            return (Calendar.getInstance().timeInMillis - vehicle.checkInTime.timeInMillis) / MINUTES_IN_MILISECOND
        }

    fun checkOutVehicle(plate: String) {
        if(vehicle.plate == plate){

            removeVehicle(vehicle)
            onSuccess(calculateFee(vehicle.type, parkedTime.toInt()))
        }
        else {
            onError()
        }
    }

    fun calculateFee(type: VehicleType, parkedTime: Int, hasDiscountCard: Boolean) : Int {
        var amountToPay = type.price

        if (parkedTime > 120){
            var extraTime = parkedTime - 120
            var extraAmount = ((extraTime / 15).toInt() + extraTime.mod(15))  * 5
            amountToPay += extraAmount
            if (hasDiscountCard) {
                amountToPay = (amountToPay - (amountToPay * 0.15)).toInt()
            }
        }
        return amountToPay
    }

    fun onSuccess(amountToPay: Int){

    }

    fun onError(){

    }

    fun removeVehicle(vehicle: Vehicle){
        parking.vehicles.remove(vehicle)
    }
}
