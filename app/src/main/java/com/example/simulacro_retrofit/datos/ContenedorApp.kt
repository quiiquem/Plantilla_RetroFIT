package com.example.simulacro_retrofit.datos

import com.example.simulacro_retrofit.conexion.RepositorioServicioApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


//Interface para tener variables en las clases
interface ContenedorApp{

    val ejemplo_repositorio: RepositorioEjemplo
}

//Base insprenscindible
class AppContenedor: ContenedorApp{
    private val baseUrl = "http://10.0.2.2:3000"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()


    //crear los servicios para la app (necesita su interface en conexionAPI antes)
private val servicioRetrofitPlantilla: RepositorioServicioApi by lazy{
    retrofit.create(RepositorioServicioApi::class.java)
}

    //llamar a la interface del repositorio
    override val ejemplo_repositorio: RepositorioEjemplo by lazy{
        ConexionEjemploRepositorio(servicioRetrofitPlantilla)
    }

}