package domain

import java.util.*

/*
    Exercise 1

    Vehicles are defined as a Set, since it is a collection that does not allow duplicates
    and satisfies the requirement of:
    'When a vehicle is going to be entered, the license plate and type are entered, and it is validated
    that there is no other vehicle with that same license plate in the parking lot.'
 */

class Vehicle(val plate: String, val type: VehicleType,var checkInTime: Calendar, var discountCard: String? = null)  {

    // Function states that two Vehicles are equal if their plates are equal
    override fun equals(other: Any?): Boolean {
        if(other is Vehicle) {
            return this.plate == other.plate
        }
        return super.equals(other)
    }

    // Function states that the hashCode(Used internally in search functions
    // in sets and arrays) is the hashCode of the plate
    override fun hashCode(): Int = this.plate.hashCode()

}
