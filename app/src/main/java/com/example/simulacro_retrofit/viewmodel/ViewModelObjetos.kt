package com.example.simulacro_retrofit.viewmodel

import android.net.http.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.simulacro_retrofit.datos.Nombre_Clase_App
import com.example.simulacro_retrofit.datos.PlantillaDAO_Repositorio
import com.example.simulacro_retrofit.datos.RepositorioEjemplo
import com.example.simulacro_retrofit.modelo.Campo_SQLite
import com.example.simulacro_retrofit.modelo.objeto_ejemplo
import kotlinx.coroutines.launch
import java.io.IOException


//VIEWMODEL 1: PARTE RETROFIT

sealed interface  PlantillaUIState{

    //data class para las listas
    data class ObtenerLista(val objetos: List<objeto_ejemplo>) : PlantillaUIState

    //data class para crear objeto
    data class InsertarObjeto(val objeto: objeto_ejemplo) : PlantillaUIState

    //data class para actualizar
    data class ActualizarExito(val objeto: objeto_ejemplo) : PlantillaUIState

    //data class para eliminar objeto
    data class EliminarExito(val id: String) : PlantillaUIState


    //objects (osea, situaciones para cargar diferentes pantallas)
    object Error: PlantillaUIState

    object Cargando: PlantillaUIState

}


//PART 1: CLASE DEL VIEWMODEL (poner UIState, funciones, etc.)
class PlantillaViewModel(private val plantillaRepositorio: RepositorioEjemplo): ViewModel() {

    //preparar un UIState para que muestre cargnado
    var plantillaUIState: PlantillaUIState by mutableStateOf(PlantillaUIState.Cargando)



    /* INIT es NECESARIO si empieza en primera pantalla algo de retrofit y necesitas una función
    init{
       obtenerLista()
    }
     */

    //Un esquema es que las funciones siempre se basan en "val nueva = repositorio.funciondelrepositorio() y luego usando el UIState se pone su funcion

    fun obtenerLista() {
        viewModelScope.launch {
            plantillaUIState = PlantillaUIState.Cargando
            plantillaUIState = try {
                val listaObjetos = plantillaRepositorio.funcion_lista_repositorio()
                PlantillaUIState.ObtenerLista(listaObjetos)

            } catch (e: IOException) {
                PlantillaUIState.Error
            } catch (e: retrofit2.HttpException) {
                PlantillaUIState.Error
            }
        }
    }


    fun insertarObjeto(objeto_parametro: objeto_ejemplo){
        viewModelScope.launch {
            plantillaUIState = PlantillaUIState.Cargando
            plantillaUIState = try{
                val modelo_nuevo  = plantillaRepositorio.insertar_objeto_repositorio(objeto_parametro)
                PlantillaUIState.InsertarObjeto(modelo_nuevo)
            } catch (e: IOException) {
                PlantillaUIState.Error
            } catch (e: retrofit2.HttpException) {
                PlantillaUIState.Error
            }
        }
    }


    fun actualizarObjeto(id_parametro: String, objeto_parametro: objeto_ejemplo){
        viewModelScope.launch{
            plantillaUIState = PlantillaUIState.Cargando
            plantillaUIState = try{
                val modelo_actualizado = plantillaRepositorio.actualizar_objeto_repositorio(id_parametro, objeto_parametro)
                PlantillaUIState.ActualizarExito(modelo_actualizado)
            } catch (e : IOException){
                PlantillaUIState.Error
            } catch (e:  retrofit2.HttpException){
                PlantillaUIState.Error
            }
        }
    }

    fun eliminarObjeto(id_parametro: String){
        viewModelScope.launch {
            plantillaUIState = PlantillaUIState.Cargando
            plantillaUIState = try {
                plantillaRepositorio.eliminar_objeto_repositorio(id_parametro)
                PlantillaUIState.EliminarExito(id_parametro)
            } catch (e: IOException){
                PlantillaUIState.Error
            } catch (e:  retrofit2.HttpException){
                PlantillaUIState.Error
            }
         }
    }

//PARTE 2: FACTORY PARA EL VIEWMODEL (NECESARIO)
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {

                val aplicacion = (this[APPLICATION_KEY] as Nombre_Clase_App)
                val Variable_Repositorio = aplicacion.modelo_Repositorio_Ejemplo
                PlantillaViewModel(plantillaRepositorio = Variable_Repositorio)
            }
        }
    }
}


//---------------------------------------
//PARTE 2 VIEWMODEL: DAOS
//---------------------------------------

//parte 1 - sealed interface
sealed interface DAO_UIState{
    data class ObtenerListaTodos(val lista_objetos: List<Campo_SQLite>): DAO_UIState
    data class ObtenerLista_Concreta(val lista_objetos_id: Campo_SQLite): DAO_UIState

    object InsertarExito: DAO_UIState
    object ActualizarExito: DAO_UIState
    object Error: DAO_UIState
    object Cargando: DAO_UIState
}


class ViewModelDAO (private val variable_RepositorioDAO: PlantillaDAO_Repositorio): ViewModel(){

    var BD_UIState: DAO_UIState by mutableStateOf(DAO_UIState.Cargando)
        private set

    var objetoPulsado: Campo_SQLite by mutableStateOf(Campo_SQLite(0, "", 0.0, 0))
        private set

    /*
    init {ObtenerListaTodos()}
     */

    fun obtener_lista_todos(){
        viewModelScope.launch {
            BD_UIState = DAO_UIState.Cargando
            BD_UIState = try{
                val lista =  variable_RepositorioDAO.obtenerTodos()
                DAO_UIState.ObtenerListaTodos(lista)
            } catch (e: IOException){
                DAO_UIState.Error
            }
        }
    }

    fun obtener_objeto_concreto(id_parametro_dao: Int){
        viewModelScope.launch {
            BD_UIState = DAO_UIState.Cargando
            BD_UIState = try{
                val objeto_concreto = variable_RepositorioDAO.obtenerObjetos_BD(id_parametro_dao)
                DAO_UIState.ObtenerLista_Concreta(objeto_concreto)
            } catch (e: IOException){
                DAO_UIState.Error
            }
        }
    }

    fun insertar_objeto(objeto_parametro_DAO: Campo_SQLite){
        viewModelScope.launch {
            BD_UIState = DAO_UIState.Cargando
            BD_UIState = try {
                 variable_RepositorioDAO.insertar(objeto_parametro_DAO)
                DAO_UIState.InsertarExito
            } catch (e: IOException){
                DAO_UIState.Error
            }
        }
    }

    fun actualizar_objeto(campoSqlite: Campo_SQLite){
        viewModelScope.launch {
            BD_UIState = try{
                variable_RepositorioDAO.actualizar(campoSqlite)
                DAO_UIState.ActualizarExito
            } catch (e: IOException){
                DAO_UIState.Error
            }
        }
    }

}
