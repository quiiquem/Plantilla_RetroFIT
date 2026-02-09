package com.example.simulacro_retrofit.modelo

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class Rutas<T : Any>(
    @StringRes val nombre: Int,
    val ruta: T,
    val iconoLleno: ImageVector,
    val iconoVacio: ImageVector
)