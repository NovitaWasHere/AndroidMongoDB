package com.example.aplicacionmusica.modelo

data class Usuario(
    val nombre: String = "",
    val apellidos: String = "",
    val correo: String = "",
    val contra: String = "",
    val instrumentos: List<Instrumento>
) {

    val _id: String = ""


    constructor( instrumentos: List<Instrumento>) : this("", "", "", "", instrumentos)

}
