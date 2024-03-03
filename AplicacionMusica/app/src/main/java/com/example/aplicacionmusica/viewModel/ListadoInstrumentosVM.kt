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

class ListadoInstrumentosVM: ViewModel() {

    private val _instrumentos = MutableLiveData<List<Instrumento>>()
    val instrumentos: LiveData<List<Instrumento>> = _instrumentos

    init {

        viewModelScope.launch{
            getInstrumentos()
        }

    }

    suspend fun getInstrumentos() {
        try {
            val result = api.getInstrumentos()
            if (result.isSuccessful) {
                val response: RespuestaApi<List<Instrumento>> = result.body()!!
                Log.d("Datos de respuesta", response.toString())
                if (response.exito == true){
                    Log.d("Datos Instrumentos", response.datos.toString())
                    _instrumentos.value = response.datos!!
                }
            }
        } catch (e: Exception) {
            Log.e("ViewModel", "Error en getInstrumentos()", e)
            // Maneja los errores aquí
        }
    }

}