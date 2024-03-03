package com.example.aplicacionmusica.navegacion

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aplicacionmusica.viewModel.AddIVM
import com.example.aplicacionmusica.viewModel.DelVM
import com.example.aplicacionmusica.viewModel.InstrumentoVM
import com.example.aplicacionmusica.viewModel.ListadoInstrumentosVM
import com.example.aplicacionmusica.viewModel.LoginVM
import com.example.aplicacionmusica.viewModel.PassVM
import com.example.aplicacionmusica.viewModel.RegisterVM
import com.example.aplicacionmusica.viewModel.UserVM
import com.example.aplicacionmusica.vista.AddInstrument
import com.example.aplicacionmusica.vista.CarritoInstrumento
import com.example.aplicacionmusica.vista.Home
import com.example.aplicacionmusica.vista.HomeLog
import com.example.aplicacionmusica.vista.Instrument
import com.example.aplicacionmusica.vista.Login
import com.example.aplicacionmusica.vista.PassCh
import com.example.aplicacionmusica.vista.Register
import com.example.aplicacionmusica.vista.User


@Composable
fun Navegacion() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Vistas.HomeLog.ruta) {
        composable("${Vistas.Login.ruta}") {
            Login(
                navController = navController,
                viewModel = LoginVM()
            )
        }
        composable("${Vistas.Register.ruta}") {
            Register(
                navController = navController,
                viewModel = RegisterVM()
            )
        }
        composable(
            route = "${ Vistas.Home.ruta}?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString("id")
            if (id != null) {
                Log.d("holi",id)
                Home(
                    navController = navController,
                    viewModel = ListadoInstrumentosVM(),
                    viewModelu = UserVM(id)
                )
            }
        }
        composable(
            route = "${ Vistas.HomeLog.ruta}",

        ) {
                HomeLog(
                    navController = navController,
                    viewModel = ListadoInstrumentosVM(),
                )

        }
        composable(
            route = "${Vistas.AddI.ruta}?id={id}",
            arguments = listOf(
            navArgument(name = "id") {
                type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString("id")
            if( id != null) {
                AddInstrument(
                    navController = navController,
                    viewModel =UserVM(id) ,
                    viewModelI = AddIVM()
                )
            }
        }
        composable(
            route = "${Vistas.Instrument.ruta}?id={id}&idu={idu}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                } ,
                navArgument(name = "idu") {
                    type = NavType.StringType
                }
            )

        ) {
            val idu = it.arguments?.getString("idu")
            val id = it.arguments?.getString("id")

            if (id != null && idu != null) {
                Instrument(
                    navController = navController,
                    viewModel = InstrumentoVM(id),
                    viewModelu = UserVM(idu)
                )
            }
        }
        composable(
            route = "${Vistas.User.ruta}?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )) {
            val id = it.arguments?.getString("id")
            if (id != null) {
                User(
                    navController = navController,
                    viewModel = UserVM(id),
                    viewModels = DelVM()
                )
            }
        }
        composable(
            route = "${Vistas.PassCh.ruta}?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )) {
            val id = it.arguments?.getString("id")
            if (id != null) {
                PassCh(
                    navController = navController,
                    viewModel = PassVM(),
                    viewModelu = UserVM(id)
                )
            }
        }
        composable(
            route = "${Vistas.Shopping.ruta}?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )) {
            val id = it.arguments?.getString("id")
            if (id != null) {
                CarritoInstrumento(
                    navController = navController,
                    viewModelu = UserVM(id)
                )
            }
        }
    }
}