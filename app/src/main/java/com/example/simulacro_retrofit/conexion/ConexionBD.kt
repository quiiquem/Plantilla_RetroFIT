package com.example.simulacro_retrofit.conexion

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simulacro_retrofit.DAO.Interfaz_DAO
import com.example.simulacro_retrofit.modelo.Campo_SQLite


@Database(entities = [Campo_SQLite::class], version = 1, exportSchema = false)
abstract class ConexionBaseDatos: RoomDatabase() {

    //funcion abstracta entre el DAO y la parte de interfaz (Interfaz_DAO esta en EjemploDAO)
    abstract fun inventarioDAO(): Interfaz_DAO

    companion object{
        @Volatile
        private var Instance: ConexionBaseDatos? = null

        fun obtenerBaseDatos(context: Context): ConexionBaseDatos{
            return Instance?: synchronized(this){
                Room.databaseBuilder(context, ConexionBaseDatos::class.java, "nombredelabase")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
