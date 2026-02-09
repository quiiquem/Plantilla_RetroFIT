package com.example.simulacro_retrofit.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class objeto_ejemplo(
    @SerialName(value = "id")
    val id: Int = 0,
    @SerialName(value = "nombre")
    val nom: String,
    @SerialName(value = "num")
    val num: Int,
    @SerialName(value = "lista")
    val lista: List<objeto_ejemplo>
)