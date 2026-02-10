package com.example.simulacro_retrofit.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.simulacro_retrofit.modelo.Campo_SQLite

//La parte de DAO, es la misma lógica que tenemos en acceso a datos
@Dao
interface Interfaz_ObjetoDAO {

    //Campo_SQLite es el nombre del objeto de DAO (esta en modelo)
    //Los metodos que tengan un ":" despues del cierre de parentesis es que SI devuelven algo

    //Listar todos en orden ascendente
    @Query("SELECT * from nombre_tabla ORDER BY nombre ASC")
    suspend fun obtenerTodos(): List<Campo_SQLite>

    //Listar todos por ID
    @Query("SELECT * from nombre_tabla WHERE id = :id")
    suspend fun obtenerTodos_Concreto(id: Int): Campo_SQLite

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(campoSqlite: Campo_SQLite)

    @Update
    suspend fun actualizar(campoSqlite: Campo_SQLite)

    @Delete
    suspend fun eliminar(campoSqlite: Campo_SQLite)
}