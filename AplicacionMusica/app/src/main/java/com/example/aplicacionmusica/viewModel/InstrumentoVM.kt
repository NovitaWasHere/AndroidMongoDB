package com.example.aplicacionmusica.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionmusica.modelo.Instrumento
import com.example.aplicacionmusica.modelo.RespuestaApi
import com.example.aplicacionmusica.retrofit.InstanciaRetrofit.RetrofitInstance.api
import kotlinx.coroutines.launch

class InstrumentoVM (id:String): ViewModel() {

    private val _instrumento = MutableLiveData<Instrumento>()
    val instrumento: LiveData<Instrumento> = _instrumento


    init {

        viewModelScope.launch{
            getInstrumento(id)
        }

      }

    suspend fun getInstrumento(id: String) {
        try {
            val result = api.getInstrumento(id)
            if (result.isSuccessful) {
                val response: RespuestaApi<Instrumento> = result.body()!!
                if (response.exito == true){
                    Log.d("Datos Instrumento unico", response.datos.toString())
                    _instrumento.value = response.datos!!
                }
            }
        } catch (e: Exception) {
            Log.e("ViewModel", "Error en getInstrumento()", e)
            // Maneja los errores aquí
        }
    }

}