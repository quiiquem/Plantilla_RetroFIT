package com.example.simulacro_retrofit

import android.app.Application
import com.example.simulacro_retrofit.datos.AppContenedor
import com.example.simulacro_retrofit.datos.ContenedorApp

class Nombre_Aplicacion : Application() {
    lateinit var contenedor: ContenedorApp
    override fun onCreate(){
        super.onCreate()
        contenedor = AppContenedor()
    }
}