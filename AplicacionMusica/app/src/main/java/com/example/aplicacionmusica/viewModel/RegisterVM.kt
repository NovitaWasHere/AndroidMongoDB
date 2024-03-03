package com.example.aplicacionmusica.viewModel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.aplicacionmusica.modelo.RespuestaApi
import com.example.aplicacionmusica.modelo.Usuario
import com.example.aplicacionmusica.navegacion.Vistas
import com.example.aplicacionmusica.retrofit.InstanciaRetrofit.RetrofitInstance.api
import kotlinx.coroutines.launch

class RegisterVM: ViewModel() {
    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre

    private val _apellidos = MutableLiveData<String>()
    val apellidos: LiveData<String> = _apellidos

    private val _contrasena = MutableLiveData<String>()
    val contrasena: LiveData<String> = _contrasena

    private val _correo = MutableLiveData<String>()
    val correo: LiveData<String> = _correo

    private val _botonActivo = MutableLiveData<Boolean>()
    val botonActivo : LiveData<Boolean> = _botonActivo

    fun CambiarInputs(nombre: String, apellidos: String,contra: String,correo: String) {
        _nombre.value = nombre
        _apellidos.value = apellidos
        _contrasena.value = contra
        _correo.value = correo

        _botonActivo.value =
            EsNombreValido(nombre) &&
            EsApellidosValidos(apellidos) &&
            EsCorreoValido(correo) &&
            EsContraValida(contra)
    }

    private fun EsNombreValido(nombre: String?): Boolean = !nombre.isNullOrEmpty()

    private fun EsApellidosValidos(apellidos: String?): Boolean = !apellidos.isNullOrEmpty()

    private fun EsCorreoValido(correo: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(correo).matches()

    private fun EsContraValida(contra: String): Boolean = contra.length > 6


    fun Register(nombre: String, apellidos: String, contra: String, correo: String,navController: NavController){
        val usuario = Usuario(nombre, apellidos, correo, contra, emptyList())
        viewModelScope.launch {
            try {
                val result = api.addUsuario(usuario)
                Log.d("Resultado del registro", result.toString())
                if (result.isSuccessful) {
                    val response: RespuestaApi<Usuario> = result.body()!!
                    if (response.exito == true){
                        navController.navigate(Vistas.Login.ruta)
                    }
                }
            } catch (ex: Exception) {
                Log.d("fallo del registro", ex.message.toString())
            }
        }
    }
}