package com.example.aplicacionmusica.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.aplicacionmusica.modelo.Instrumento
import com.example.aplicacionmusica.modelo.RespuestaApi
import com.example.aplicacionmusica.modelo.Usuario
import com.example.aplicacionmusica.navegacion.Vistas
import com.example.aplicacionmusica.retrofit.InstanciaRetrofit.RetrofitInstance.api
import kotlinx.coroutines.launch

class UserVM(id:String): ViewModel() {

    private val _user = MutableLiveData<Usuario>()
    val user : LiveData<Usuario> = _user

    private val _botonActivo = MutableLiveData<Boolean>()
    val botonActivo : LiveData<Boolean> = _botonActivo

    init {

        viewModelScope.launch{
            getUsuario(id)
        }

    }

    fun getUsuario(id: String){
        viewModelScope.launch {
            try {
                val result = api.getUsuario(id)
                Log.d("Resultado de inicio", result.toString())
                if (result.isSuccessful) {
                    val response: RespuestaApi<Usuario> = result.body()!!
                    if (response.exito == true){

                        _user.value = response.datos!!
                    }
                }
            } catch (ex: Exception) {
                Log.d("Fallo consiguiendo un usuario", ex.message.toString())
            }
        }
    }

    fun addToCart(instrumento: Instrumento,navController: NavController,id:String){

        viewModelScope.launch {
            try {
                val result = api.actUsuario(id,instrumento._id)
                Log.d("Resultado de inicio", result.toString())
                if (result.isSuccessful) {
                    val response: RespuestaApi<Usuario> = result.body()!!
                    if (response.exito == true){

                        navController.navigate(Vistas.Home.ruta+"?id="+id)

                    }
                }
            } catch (ex: Exception) {
                Log.d("Fallo consiguiendo un usuario", ex.message.toString())
            }
        }

    }
}