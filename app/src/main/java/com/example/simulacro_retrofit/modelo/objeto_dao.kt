package com.example.simulacro_retrofit.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey


//Objetos que se guardaran en la tabla de base de datos (productos, por ejemplo)
@Entity(tableName="Nombre_Tabla")
data class Campo_SQLite(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val precio: Double,
    val cantidad: Int
)