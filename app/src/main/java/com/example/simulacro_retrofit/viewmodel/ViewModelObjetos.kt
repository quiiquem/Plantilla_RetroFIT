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
import com.example.simulacro_retrofit.datos.RepositorioEjemplo
import com.example.simulacro_retrofit.modelo.objeto_ejemplo
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface  PlantillaUIState{

    //data class para las listas
    data class ObtenerLista(val objetos: List<objeto_ejemplo>) : PlantillaUIState

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


//PART 1: CLASE DEL VIEWMODEL (poner UIState, funciones, etc.)
class PlantillaViewModel(private val plantillaRepositorio: RepositorioEjemplo): ViewModel() {

    //preparar un UIState para que muestre cargnado
    var plantillaUIState: PlantillaUIState by mutableStateOf(PlantillaUIState.Cargando)



    /* INIT es NECESARIO si empieza en primera pantalla algo de retrofit y necesitas una función
    init{
       obtener_Lista()
    }
     */

    fun funcion_lista(){
        plantillaUIState = PlantillaUIState.Cargando
        plantillaUIState = try{
            val listaObjetos = plantillaRepositorio.funcion_lista()
            PlantillaUIState.ObtenerLista(listaObjetos)

        } catch (e: IOException){
            PlantillaUIState.Error
        } catch(e: HttpException){
            PlantillaUIState.Error
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

