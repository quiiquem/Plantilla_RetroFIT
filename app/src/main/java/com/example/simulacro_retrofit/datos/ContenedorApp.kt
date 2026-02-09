package com.example.simulacro_retrofit.datos

import com.example.simulacro_retrofit.conexion.Repositorio_Modelo_ServicioAPI
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface ContenedorApp{
    val modelo_Repositorio_Ejemplo: RepositorioEjemplo
}


class Nombre_Clase_App:  ContenedorApp{

    //URL del servidor local
    private val BASE_URL = "http://10.0.2.2:3000/"

    //Builder de Retrofit
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()


    //REPOSITORIOS (necesitamos haber puesto el serviciAPI en conexionAPI antes (y repositorios creados))
    private val servicio_Retrofit_Objeto: Repositorio_Modelo_ServicioAPI by lazy{
        retrofit.create(Repositorio_Modelo_ServicioAPI::class.java)
    }

    override val modelo_Repositorio_Ejemplo: RepositorioEjemplo by lazy{

        //[Nombre de la conexion del repositorio (en su fichero)] ([nombre servicioRetrofit])
        ConexionEjemploRepositorio(servicio_Retrofit_Objeto)
    }

}