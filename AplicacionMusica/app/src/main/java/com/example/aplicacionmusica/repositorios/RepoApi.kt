package com.example.aplicacionmusica.repositorios

import com.example.aplicacionmusica.modelo.Instrumento
import com.example.aplicacionmusica.modelo.RespuestaApi
import com.example.aplicacionmusica.modelo.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RepoApi {

    //Instruments

    @GET("api/instrumentos/todos")
    suspend fun getInstrumentos(): Response<RespuestaApi<List<Instrumento>>>

    @GET("api/instrumentos/esp/{id}")
    suspend fun getInstrumento(@Path("id") id: String): Response<RespuestaApi<Instrumento>>

    @POST("api/instrumentos/agregar")
    suspend fun addInstrumento(@Body intrument: Instrumento): Response<RespuestaApi<Instrumento>>


    //Users

    @GET("api/usuarios/esp/{id}")
    suspend fun getUsuario(@Path("id") id: String): Response<RespuestaApi<Usuario>>

    @POST("api/usuarios/agregar")
    suspend fun addUsuario(@Body usuario: Usuario): Response<RespuestaApi<Usuario>>

    @POST("api/usuarios/auth")
    suspend fun authUsuario(@Body usuario: Usuario): Response<RespuestaApi<Usuario>>

    @PUT("api/usuarios/{id}")
    suspend fun actUsuario(@Path("id") id: String, @Body usuario: Usuario): Response<RespuestaApi<Usuario>>

    @PUT("api/usuarios/agregarCarrito/{id}/ins/{idI}")
    suspend fun actUsuario(@Path("id") id: String,@Path("idI") idI: String): Response<RespuestaApi<Usuario>>

    @DELETE("api/usuarios/{id}")
    suspend fun deleteUsuario(@Path("id") id: String): Response<RespuestaApi<Usuario>>
}