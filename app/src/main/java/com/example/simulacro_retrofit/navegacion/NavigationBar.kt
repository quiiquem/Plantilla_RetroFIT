package com.example.simulacro_retrofit.navegacion
import com.example.simulacro_retrofit.R
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simulacro_retrofit.modelo.Rutas
import com.example.simulacro_retrofit.ui.pantallas.PantallaInicio
import com.example.simulacro_retrofit.ui.pantallas.PantallaInsertar
import com.example.simulacro_retrofit.ui.pantallas.PantallaLista
import com.example.simulacro_retrofit.viewmodel.PlantillaViewModel

enum class Pantallas(@StringRes val titulo: Int) {
    Inicio(titulo = R.string.pantalla_inicio),
    Insertar(titulo = R.string.pantalla_insertar),
    Listar(titulo = R.string.pantalla_listar),

    Actualizar(titulo = R.string.pantalla_actualizar)
}


val listaRutas = listOf(
    Rutas(Pantallas.Inicio.titulo, Pantallas.Inicio.name, Icons.Filled.Home, Icons.Outlined.Home),
    Rutas(Pantallas.Listar.titulo, Pantallas.Listar.name,  Icons.Filled.List, Icons.Outlined.List),
    Rutas(Pantallas.Actualizar.titulo, Pantallas.Actualizar.name, Icons.Filled.Edit, Icons.Outlined.Edit),
    Rutas(Pantallas.Insertar.titulo, Pantallas.Insertar.name, Icons.Filled.Add, Icons.Outlined.Add)
)


@Composable
fun NavegacionBar(
    navController: NavHostController = rememberNavController()
    ,
    viewModel_Ejemplo: PlantillaViewModel = viewModel(factory = PlantillaViewModel.Factory)
){
    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                listaRutas.forEachIndexed { indice, ruta ->
                    NavigationBarItem(
                        icon = {
                            if(selectedItem == indice)
                                Icon(
                                    imageVector = ruta.iconoLleno,
                                    contentDescription = stringResource(id = ruta.nombre)
                                )
                            else
                                Icon(
                                    imageVector = ruta.iconoVacio,
                                    contentDescription = stringResource(id = ruta.nombre)
                                )
                        },
                        label = { Text(stringResource(id = ruta.nombre)) },
                        selected = selectedItem == indice,
                        onClick = {
                            selectedItem = indice
                            navController.navigate(ruta.ruta)
                        }
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Pantallas.Inicio.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Grafo de las rutas
            composable(route = Pantallas.Inicio.name) {
                PantallaInicio(
                    modifier = Modifier
                        .fillMaxSize(),

                )
            }
            composable(route = Pantallas.Insertar.name) {
                PantallaInsertar(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            composable(route = Pantallas.Listar.name) {
                PantallaLista(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            composable(route = Pantallas.Actualizar.name) {
                PantallaLista(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}