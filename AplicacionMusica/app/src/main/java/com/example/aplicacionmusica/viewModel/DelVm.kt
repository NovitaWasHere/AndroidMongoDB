package com.example.aplicacionmusica.viewModel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.aplicacionmusica.navegacion.Vistas
import com.example.aplicacionmusica.retrofit.InstanciaRetrofit.RetrofitInstance.api
import kotlinx.coroutines.launch

class DelVM: ViewModel() {

    fun Delate(id:String,navController: NavController){

    viewModelScope.launch {
            try {
                api.deleteUsuario(id)

                val result =api.getUsuario(id)

                   if (result.isSuccessful) {

                       navController.navigate(Vistas.HomeLog.ruta)

                   }else{
                       navController.navigate(Vistas.HomeLog.ruta)
                   }

            } catch (ex: Exception) {
                Log.d("fallo del registro", ex.message.toString())
            }
        }

    }
}