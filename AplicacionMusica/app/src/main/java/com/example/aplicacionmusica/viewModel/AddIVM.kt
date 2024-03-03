package com.example.aplicacionmusica.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.aplicacionmusica.modelo.Instrumento
import com.example.aplicacionmusica.modelo.RespuestaApi
import com.example.aplicacionmusica.navegacion.Vistas
import com.example.aplicacionmusica.retrofit.InstanciaRetrofit.RetrofitInstance.api
import kotlinx.coroutines.launch

class AddIVM: ViewModel() {

    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre

    private val _precio = MutableLiveData<String>()
    val precio: LiveData<String> = _precio

    private val _descripcion = MutableLiveData<String>()
    val descripcion: LiveData<String> = _descripcion

    private val _botonActivo = MutableLiveData<Boolean>()
    val botonActivo : LiveData<Boolean> = _botonActivo


    fun CambiarInputs(nombre: String, precio: String,descripcion: String) {
        _nombre.value = nombre
        _precio.value = precio
        _descripcion.value = descripcion

        _botonActivo.value = nombre(nombre) && precio(precio) && desc(descripcion)
    }

    private fun nombre(nombre: String?): Boolean = !nombre.isNullOrEmpty()

    private fun precio(precio: String?): Boolean = !precio.isNullOrEmpty()

    private fun desc(descripcion: String): Boolean =  !descripcion.isNullOrEmpty()


    fun RegisterI(nombre: String, precio: String, desc: String,navController: NavController,id:String){

        val numberValue: Number = precio.toDouble()

        val instrumento = Instrumento(nombre, desc, true, numberValue)
        viewModelScope.launch {
            try {
                val result = api.addInstrumento(instrumento)
                Log.d("Resultado del registro", result.toString())
                if (result.isSuccessful) {
                    val response: RespuestaApi<Instrumento> = result.body()!!
                    if (response.exito == true){
                        navController.navigate(Vistas.Home.ruta + "?id=${id}")
                    }
                }
            } catch (ex: Exception) {
                Log.d("fallo del registro", ex.message.toString())
            }
        }
    }
}