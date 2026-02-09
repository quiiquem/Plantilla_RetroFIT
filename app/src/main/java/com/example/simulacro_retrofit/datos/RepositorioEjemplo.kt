package com.example.simulacro_retrofit.datos

import com.example.simulacro_retrofit.conexion.Repositorio_Modelo_ServicioAPI
import com.example.simulacro_retrofit.modelo.objeto_ejemplo
import com.example.simulacro_retrofit.modelo.objeto_secundario_lista

//interfaz (basicamente declarar funciones para la API)
interface RepositorioEjemplo{

    suspend fun obtenerLista(): List<objeto_ejemplo>
    suspend fun insertarObjeto(objeto: objeto_ejemplo): objeto_ejemplo
    suspend fun actualizarObjeto(id: String, objeto: objeto_ejemplo): objeto_ejemplo
    suspend fun eliminarObjeto(id: String): objeto_ejemplo
}

interface RepositorioSecundario{

    suspend fun obtenerListaSecu(): List<objeto_secundario_lista>

    //y igual pero con otro modelo
}


//Clase de conexion a la API y ContenedorApp
class ConexionEjemploRepositorio(
    private val conexion_servicioApi: Repositorio_Modelo_ServicioAPI
):  RepositorioEjemplo{
    override suspend fun obtenerLista(): List<objeto_ejemplo> = conexion_servicioApi.funcion_lista()
    override suspend fun insertarObjeto(objeto: objeto_ejemplo): objeto_ejemplo = conexion_servicioApi.insertarobjeto_api(objeto)
    override suspend fun actualizarObjeto(id: String, objeto: objeto_ejemplo): objeto_ejemplo = conexion_servicioApi.actualizarobjeto_api(id, objeto)
    override suspend fun eliminarObjeto(id: String) = conexion_servicioApi.eliminarobjeto_api(id)
}
