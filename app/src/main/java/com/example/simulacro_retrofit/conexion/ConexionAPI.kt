package com.example.simulacro_retrofit.conexion

import com.example.simulacro_retrofit.modelo.objeto_ejemplo
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


//Crear Interfaz para crear el servicioAPI en repositorio y conectar metodos del viewmodel
    interface Repositorio_Modelo_ServicioAPI{

        //ahora obtenerLista, insertarObjeto, etc. sera para el viewmodel igual
        @GET("objetodeljson")
        suspend fun lista_api(): List<objeto_ejemplo>
        @POST("objetodeljson")
        suspend fun insertarobjeto_api(
            @Body objeto: objeto_ejemplo
        ): objeto_ejemplo

        @PUT("objetodeljson/{id}")
        suspend fun actualizarobjeto_api(
            @Path("id") id: String,
            @Body objeto: objeto_ejemplo
        ): objeto_ejemplo

        @DELETE("objetodeljson/{id}")
        suspend fun eliminarobjeto_api(
            @Path("id") id: String
        ): objeto_ejemplo
    }


   // interface Repositorio_ModeloSecundario_ServicioAPI