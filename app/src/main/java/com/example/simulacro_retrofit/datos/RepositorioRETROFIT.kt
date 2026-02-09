package com.example.simulacro_retrofit.datos

import com.example.simulacro_retrofit.conexion.Repositorio_Modelo_ServicioAPI
import com.example.simulacro_retrofit.modelo.objeto_ejemplo
import com.example.simulacro_retrofit.modelo.objeto_secundario_lista

//interfaz (basicamente declarar funciones para la API)
interface RepositorioEjemplo{

    suspend fun funcion_lista_repositorio(): List<objeto_ejemplo>
    suspend fun insertar_objeto_repositorio(objeto: objeto_ejemplo): objeto_ejemplo
    suspend fun actualizar_objeto_repositorio(id: String, objeto: objeto_ejemplo): objeto_ejemplo
    suspend fun eliminar_objeto_repositorio(id: String): objeto_ejemplo
}

interface RepositorioSecundario{

    suspend fun obtenerListaSecu(): List<objeto_secundario_lista>

    //y igual pero con otro modelo
}


//Clase de conexion a la API y ContenedorApp (conexionAPI: nombre del repositorio en contenedorapp)
class ConexionEjemploRepositorio(
    private val conexion_servicioApi: Repositorio_Modelo_ServicioAPI
):  RepositorioEjemplo{
    override suspend fun funcion_lista_repositorio(): List<objeto_ejemplo> = conexion_servicioApi.lista_api()
    override suspend fun insertar_objeto_repositorio(objeto: objeto_ejemplo): objeto_ejemplo = conexion_servicioApi.insertarobjeto_api(objeto)
    override suspend fun actualizar_objeto_repositorio(id: String, objeto: objeto_ejemplo): objeto_ejemplo = conexion_servicioApi.actualizarobjeto_api(id, objeto)
    override suspend fun eliminar_objeto_repositorio(id: String) = conexion_servicioApi.eliminarobjeto_api(id)
}
