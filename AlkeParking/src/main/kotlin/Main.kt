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

    for(v in 1..20){
        val newCar = Vehicle("AAAAAA$v", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_00$v")
        if (parking.addVehicle(newCar)) {
            println("Welcome to AlkeParking!")
        }
        else {
            println("Sorry, the check-in failed")
        }
    }


    val parkingSpace: ParkingSpace = ParkingSpace(car, parking)

    parkingSpace.checkOutVehicle(car.plate)

    val invalidCar = Vehicle("BC11134", VehicleType.CAR, cal, "DISCOUNT_CARD_001")
    parkingSpace.checkOutVehicle(invalidCar.plate)

    //val car2 = Vehicle("AA111A345A", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_003")


    /*
    println(isCar2Inserted)


    println(parking.vehicles.contains(car))
    println(parking.vehicles.contains(motorbike))
    println(parking.vehicles.contains(minibus))
    println(parking.vehicles.contains(bus))
    println(parking.vehicles.contains(car2)) // PREGUNTAR


    parking.vehicles.remove(motorbike)
    println(parking.vehicles.contains(motorbike))

     */


}