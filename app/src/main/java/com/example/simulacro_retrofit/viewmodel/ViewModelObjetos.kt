package com.example.simulacro_retrofit.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simulacro_retrofit.datos.RepositorioEjemplo
import com.example.simulacro_retrofit.modelo.objeto_ejemplo
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface  PlantillaUIState{

    //data class para las listas
    data class obtenerLista(val objetos: List<objeto_ejemplo>) : PlantillaUIState

    //data class para crear objeto
    data class CrearExito(val objeto: objeto_ejemplo) : PlantillaUIState

    //data class para actualizar
    data class ActualizarExito(val objeto: objeto_ejemplo) : PlantillaUIState

    //data class para eliminar objeto
    data class EliminarExito(val id: String) : PlantillaUIState


    //objects (osea, situaciones para cargar diferentes pantallas)
    object Error: PlantillaUIState

    object Cargando: PlantillaUIState

}

class PlantillaViewModel(private val plantillaRepositorio: RepositorioEjemplo): ViewModel(){

  //preparar un UIState para que muestre cargnado
  var plantillaUIState: PlantillaUIState by mutableStateOf(PlantillaUIState.Cargando)



    fun obtenerLista(){
        viewModelScope.launch{
            plantillaUIState = PlantillaUIState.Cargando
                plantillaUIState = try{
                     val listaObjetos = plantillaRepositorio.obtenerLista()
                    plantillaUIState.obtenerLista(listaObjetos)
                } catch (e: IOException){
                    PlantillaUIState.Error
                }
        }
    }


}