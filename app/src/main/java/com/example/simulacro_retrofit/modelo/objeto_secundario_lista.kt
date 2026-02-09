package com.example.simulacro_retrofit.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class objeto_secundario_lista(
    @SerialName(value = "id")
    val id: Int = 0,
    @SerialName(value = "nombre")
    val nombre: String = "",
)
