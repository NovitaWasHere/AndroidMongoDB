package com.example.aplicacionmusica.viewModel

import android.util.Log
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

class PassVM: ViewModel() {

    private val _contrasena = MutableLiveData<String>()
    val contrasena: LiveData<String> = _contrasena

    private val _contrasenaA = MutableLiveData<String>()
    val contrasenaA: LiveData<String> =  _contrasenaA

    private val _botonActivo = MutableLiveData<Boolean>()
    val botonActivo : LiveData<Boolean> = _botonActivo

    fun CambiarInputs(contraA: String,contra: String) {

        _contrasenaA.value = contraA
        _contrasena.value = contra

        _botonActivo.value =
            ContraA(contraA) && Contra(contra)
    }


    private fun Contra(contra: String): Boolean =  !contra.isNullOrEmpty()

    private fun ContraA(contra: String?): Boolean = !contra.isNullOrEmpty()

    fun Cambiar(correo:String ,contraA: String,contra:String,navController: NavController){

        val usuario = Usuario("","",correo,contraA, emptyList())

        viewModelScope.launch {
            try {
                val result = api.authUsuario(usuario)
                Log.d("Resultado del registro", result.toString())
                if (result.isSuccessful) {
                    val response: RespuestaApi<Usuario> = result.body()!!
                    val id = response.datos
                    if (id != null) {
                        if (response.exito == true && id._id != null) {

                            Log.d("id",id._id)
                            val nuevoU = Usuario(id.nombre,id.apellidos,correo,contra, emptyList())
                            Log.d("nuevoU",nuevoU.toString())

                            val act = api.actUsuario(id._id,nuevoU)

                            if (act.isSuccessful) {
                                Log.d("Resultado del registro", act.toString())
                                val response2: RespuestaApi<Usuario> = act.body()!!
                                if (response2.exito == true) {
                                    navController.navigate( Vistas.Home.ruta + "?id=${id._id}")
                                }

                            }
                        }
                    }
                }
            } catch (ex: Exception) {
                Log.d("fallo del registro", ex.message.toString())
            }
        }

    }
}