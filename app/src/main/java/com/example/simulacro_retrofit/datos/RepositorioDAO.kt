package com.example.simulacro_retrofit.datos

import com.example.simulacro_retrofit.DAO.Interfaz_ObjetoDAO
import com.example.simulacro_retrofit.modelo.Campo_SQLite

//Interfaz , aqui defino los metodos para el objeto de la BD
interface PlantillaDAO_Repositorio{
    suspend fun obtenerObjetos_BD(id: Int): Campo_SQLite
    suspend fun obtenerTodos(): List<Campo_SQLite>

    suspend fun insertar(campo_SQLite: Campo_SQLite)

    suspend fun actualizar(campo_SQLite: Campo_SQLite)
    suspend fun eliminar(campo_SQLite: Campo_SQLite)
}

//Implementación de repositorio al DAO para fuente de datos

class ConexionDAORepositorio(private val variable_interfaz: Interfaz_ObjetoDAO): PlantillaDAO_Repositorio {

    //Funciones de arriba implementadas

    //pillar por id
    override suspend fun obtenerObjetos_BD(id: Int): Campo_SQLite = variable_interfaz.obtenerTodos_Concreto(id)

    //pillar todos
    override suspend fun obtenerTodos(): List<Campo_SQLite> = variable_interfaz.obtenerTodos()

    //insertar
    override suspend fun insertar(campo_SQLite: Campo_SQLite) = variable_interfaz.insertar(campo_SQLite)

    //actualizar
    override suspend fun actualizar(campo_SQLite: Campo_SQLite) = variable_interfaz.actualizar(campo_SQLite)

    //eliminar
    override suspend fun eliminar(campo_SQLite: Campo_SQLite) = variable_interfaz.eliminar(campo_SQLite)

}