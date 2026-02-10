package com.example.simulacro_retrofit

import android.app.Application
import com.example.simulacro_retrofit.datos.ContenedorApp
import com.example.simulacro_retrofit.datos.Nombre_Clase_App

class Aplicacion: Application(){

    lateinit var contenedor: ContenedorApp //<- ContenedorApp es el nombre de las interfaces (que enlazan a repositorios)
    override fun onCreate(){
        super.onCreate()

        //nombre de la clase de Contenedor App
        contenedor = Nombre_Clase_App(applicationContext) //poner el contexto de la BD
    }
}