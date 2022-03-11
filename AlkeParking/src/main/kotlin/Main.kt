import domain.Parking
import domain.ParkingSpace
import domain.Vehicle
import domain.VehicleType
import java.util.*

fun main() {

    val cal = Calendar.getInstance()
    cal.set(2022, 2, 9, 18, 0)

    val car = Vehicle("AA111AA", VehicleType.CAR, cal, "DISCOUNT_CARD_001")
    val motorbike = Vehicle("B222BBB", VehicleType.MOTORBIKE, Calendar.getInstance())
    val minibus = Vehicle("CC333CC", VehicleType.MINIBUS, Calendar.getInstance())
    val bus = Vehicle("DD444DD", VehicleType.BUS, Calendar.getInstance(), "DISCOUNT_CARD_002")
    val parking = Parking(mutableSetOf(car, motorbike, minibus, bus))

    val repeatedPlateCar = Vehicle("AA111AA", VehicleType.CAR, cal, "DISCOUNT_CARD_001")
    // Can we check-in a vehicle if we already have another vehicle with the same plate?
    if (!parking.addVehicle(repeatedPlateCar)){
        println("Check-in failed. There is another vehicle with this plate!")
    }

    for(v in 1..20){
        val newCar = Vehicle("AAAAAA$v", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_00$v")
        if (parking.addVehicle(newCar)) {
            println("$v Welcome to AlkeParking!")
        }
        else {
            println("Sorry, the check-in failed")
        }
    }


    val parkingSpace = ParkingSpace(car, parking)

    parkingSpace.checkOutVehicle(car.plate)

    val parkingSpace2 = ParkingSpace(motorbike, parking)

    parkingSpace2.checkOutVehicle(motorbike.plate)

    parking.checkoutHistory()

    parking.listVehicles()

    val unregisteredCar = Vehicle("BC11134", VehicleType.CAR, cal, "DISCOUNT_CARD_001")
    val parkingSpace3 = ParkingSpace(unregisteredCar, parking)
    parkingSpace3.checkOutVehicle(unregisteredCar.plate) // Can we checkout an unregistered car?

}