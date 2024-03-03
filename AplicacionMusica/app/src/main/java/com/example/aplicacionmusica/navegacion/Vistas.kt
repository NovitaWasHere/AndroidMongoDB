package com.example.aplicacionmusica.navegacion

sealed class Vistas(val ruta: String) {

    object Login: Vistas("Login")
    object Home: Vistas("Home")
    object HomeLog: Vistas("HomeLog")
    object User: Vistas("User")
    object PassCh: Vistas("PassCh")
    object AddI: Vistas("AddInstrument")
    object Instrument: Vistas("Instrument")
    object Register: Vistas("Register")
    object Shopping: Vistas("Shopping")
}