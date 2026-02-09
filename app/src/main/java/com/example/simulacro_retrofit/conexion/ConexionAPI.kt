package com.example.simulacro_retrofit.conexion

import com.example.simulacro_retrofit.modelo.objeto_ejemplo
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


    interface RepositorioServicioApi{

        @GET("objetodeljson")
        suspend fun obtenerLista(): List<objeto_ejemplo>


        @POST("objetodeljson")
        suspend fun insertarObjeto(
            @Body objeto: objeto_ejemplo
        ): objeto_ejemplo

        @PUT("objetodeljson/{id}")
        suspend fun actualizarObjeto(
            @Path("id") id: String,
            @Body objeto: objeto_ejemplo
        ): objeto_ejemplo

        @DELETE("objetodeljson/{id}")
        suspend fun eliminarObjeto(
            @Path("id") id: String
        ): objeto_ejemplo
    }

