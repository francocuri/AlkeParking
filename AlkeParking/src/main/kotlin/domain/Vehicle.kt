package domain

import java.util.*

/*
    Ejercicio 1
    Se define vehicles como un Set ya que es una colección que no admite repetidos
    y satisface la consigna de:
    Cuando se va a ingresar un vehículo se ingresa la placa y el tipo, y se valida
    que no haya ningún otro vehículo con esa misma placa en el estacionamiento.
 */

/*
    Ejercicio 2
    Consigna 2) No puede cambiar el tipo del vehículo en el tiempo,
    por lo tanto definimos la constante 'type' por constructor
 */

class Vehicle(val plate: String, val type: VehicleType,var checkInTime: Calendar, var discountCard: String? = null)  {

    init {

    }
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
