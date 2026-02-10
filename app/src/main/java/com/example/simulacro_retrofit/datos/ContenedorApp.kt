package com.example.simulacro_retrofit.datos

import android.content.Context
import com.example.simulacro_retrofit.conexion.ConexionBaseDatos
import com.example.simulacro_retrofit.conexion.Repositorio_Modelo_ServicioAPI
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface ContenedorApp{
    val modelo_Repositorio_Ejemplo: RepositorioEjemplo

    val DAO_Repositorio: PlantillaDAO_Repositorio
}


class Nombre_Clase_App(private val context: Context):  ContenedorApp {

    // ==================== RETROFIT ====================
    //URL del servidor local
    private val BASE_URL = "http://10.0.2.2:3000/"

    //Builder de Retrofit
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()


    //Servicio Retrofit (necesitamos haber puesto el serviciAPI en conexionAPI antes (y repositorios creados))
    private val servicio_Retrofit_Objeto: Repositorio_Modelo_ServicioAPI by lazy {
        retrofit.create(Repositorio_Modelo_ServicioAPI::class.java)
    }

    //Repositorio Retrofit
    override val modelo_Repositorio_Ejemplo: RepositorioEjemplo by lazy {

        //[Nombre de la conexion del repositorio (en su fichero)] ([nombre servicioRetrofit])
        ConexionEjemploRepositorio(servicio_Retrofit_Objeto)
    }

    // ==================== ROOM ====================
    //REPOSITORIO BASE DE DATOS ROOM

    override val DAO_Repositorio: PlantillaDAO_Repositorio by lazy {
        ConexionDAORepositorio(ConexionBaseDatos.obtenerBaseDatos(context).basededatos())
    }

}

