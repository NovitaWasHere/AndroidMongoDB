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

class LoginVM: ViewModel() {
    private val _correo = MutableLiveData<String>()
    val correo : LiveData<String> = _correo

    private val _contra = MutableLiveData<String>()
    val contra : LiveData<String> = _contra

    private val _botonActivo = MutableLiveData<Boolean>()
    val botonActivo : LiveData<Boolean> = _botonActivo

    private val _noValido = MutableLiveData<Boolean>()
    val noValido : LiveData<Boolean> = _noValido


    fun CambiarInputs(correo: String, contra: String){
        _correo.value = correo
        _contra.value = contra
        _botonActivo.value = EsCorreoValido(correo) && EsContraValida(contra)
    }

    private fun EsCorreoValido(correo: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(correo).matches()

    private fun EsContraValida(contra: String): Boolean = contra.length > 6

    fun IntentarInicioSesion(correo: String, contra: String, navController: NavController){
        viewModelScope.launch {
            try {
                Log.d("Lo intenta almenos", noValido.value.toString())
                val result = api.authUsuario(Usuario("","",correo,contra,emptyList()))
                Log.d("Resultado de inicio", result.toString())
                if (result.isSuccessful) {
                    val response: RespuestaApi<Usuario> = result.body()!!
                    if (response.exito == true){
                        val usuario = response.datos!!

                        Log.d("Intenando inicio", usuario._id)

                        navController.navigate(Vistas.Home.ruta+"?id=${usuario._id}")

                    }
                }else{
                    _noValido.value = true
                }
            } catch (ex: Exception) {
                Log.d("falloInicio", ex.message.toString())
            }
        }
    }

    fun DesactivarAlerta(){
        _noValido.value = false
        _correo.value = ""
        _contra.value = ""
    }
}
