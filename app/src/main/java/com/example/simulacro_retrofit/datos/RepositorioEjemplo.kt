package com.example.simulacro_retrofit.datos

import com.example.simulacro_retrofit.conexion.RepositorioServicioApi
import com.example.simulacro_retrofit.modelo.objeto_ejemplo

interface RepositorioEjemplo{

    suspend fun obtenerLista(): List<objeto_ejemplo>
    suspend fun insertarObjeto(objeto: objeto_ejemplo): objeto_ejemplo
    suspend fun actualizarObjeto(id: String, objeto: objeto_ejemplo): objeto_ejemplo
    suspend fun eliminarObjeto(id: String): objeto_ejemplo

}


class ConexionEjemploRepositorio(
    private val ejemploServicioApi: RepositorioServicioApi
):  RepositorioEjemplo{

    override suspend fun obtenerLista(): List<objeto_ejemplo> = ejemploServicioApi.obtenerLista()
    override suspend fun insertarObjeto(objeto: objeto_ejemplo): objeto_ejemplo = ejemploServicioApi.insertarObjeto(objeto)
    override suspend fun actualizarObjeto(id: String, objeto: objeto_ejemplo): objeto_ejemplo = ejemploServicioApi.actualizarObjeto(id, objeto)
    override suspend fun eliminarObjeto(id: String) = ejemploServicioApi.eliminarObjeto(id)

}
