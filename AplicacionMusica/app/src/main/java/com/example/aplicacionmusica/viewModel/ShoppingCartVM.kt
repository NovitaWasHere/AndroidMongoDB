package com.example.aplicacionmusica.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionmusica.modelo.Instrumento
import com.example.aplicacionmusica.modelo.RespuestaApi
import com.example.aplicacionmusica.modelo.Usuario
import com.example.aplicacionmusica.retrofit.InstanciaRetrofit.RetrofitInstance.api
import kotlinx.coroutines.launch

class ShoppingCartVM(id:String): ViewModel() {

    private val _instrumentos = MutableLiveData<List<Instrumento>>()
    val instrumentos: LiveData<List<Instrumento>> = _instrumentos

    init {

        viewModelScope.launch{

            getInstrumentosShppingCart(id)

        }

    }

    private suspend fun getInstrumentosShppingCart(id:String) {
        try {
            val result = api.getUsuario(id)
            if (result.isSuccessful) {
                val response: RespuestaApi<Usuario> = result.body()!!
                Log.d("Datos de respuesta", response.toString())
                if (response.exito == true){
                    Log.d("Datos Instrumentos", response.datos.toString())
                    val instrumentose = response.datos!!.instrumentos
                    _instrumentos.value = instrumentose
                }
            }
        } catch (e: Exception) {
            Log.e("Error en el carrito", e.toString())
        }
    }


}