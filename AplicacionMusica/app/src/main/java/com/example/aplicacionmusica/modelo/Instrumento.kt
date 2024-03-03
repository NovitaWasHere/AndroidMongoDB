package com.example.aplicacionmusica.modelo

data class Instrumento(
    val nombre: String,
    val descripcion: String,
    val disponibilidad: Boolean,
    val precio: Number,

) {
    val _id: String = ""

    constructor() : this("", "", false,  0)
    
}
