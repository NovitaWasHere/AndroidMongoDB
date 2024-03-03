package com.example.aplicacionmusica.vista

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplicacionmusica.R
import com.example.aplicacionmusica.modelo.Usuario
import com.example.aplicacionmusica.navegacion.Vistas
import com.example.aplicacionmusica.viewModel.AddIVM
import com.example.aplicacionmusica.viewModel.UserVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddInstrument(viewModel: UserVM,viewModelI: AddIVM,navController: NavController){

    val usuario: Usuario by viewModel.user.observeAsState(Usuario(emptyList()))
    val botonActivo: Boolean by viewModelI.botonActivo.observeAsState(false)
    val nombre: String by viewModelI.nombre.observeAsState("")
    val precio: String by viewModelI.precio.observeAsState("")
    val descripcion: String by viewModelI.descripcion.observeAsState("")


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(id = R.color.main),
                    titleContentColor = colorResource(id = R.color.blanco_hueso),
                    navigationIconContentColor = colorResource(id = R.color.blanco_hueso),
                    actionIconContentColor = colorResource(id = R.color.blanco_hueso),
                ),
                title = {
                    Text(
                        "Agregar Instrumento",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = colorResource(id = R.color.main),
                contentColor = colorResource(id = R.color.blanco_hueso  ),
                actions = {
                    Row(horizontalArrangement = Arrangement.spacedBy(25.dp)) {
                        IconButton(onClick = {
                            navController.navigate(
                                Vistas.User.ruta + "?id=${usuario._id}"
                            ) }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(35.dp)
                                    .padding(5.dp),
                                imageVector = Icons.Filled.AccountBox,
                                contentDescription = "",
                                tint = colorResource(id = R.color.blanco_hueso)
                            )
                        }
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { navController.popBackStack()},
                        containerColor = colorResource(id = R.color.blanco_hueso),
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon( Icons.Rounded.ArrowBack,modifier = Modifier.size(30.dp), contentDescription = "", tint = colorResource(id = R.color.azul_1))
                    }
                },
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.blanco_hueso))
            .padding(innerPadding))
        {

            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Regístrar Instrumento",
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.height(20.dp))

                RegistroTextField(
                    value = nombre,
                    onValueChange = { viewModelI.CambiarInputs(it, precio, descripcion) },
                    label = "Nombre"
                )
                Spacer(modifier = Modifier.height(5.dp))


                RegistroTextField(
                    value = precio,
                    onValueChange = { viewModelI.CambiarInputs(nombre, it, descripcion) },
                    label = "Precio del instrumento"
                )
                Spacer(modifier = Modifier.height(5.dp))

                RegistroTextField(
                    value = descripcion,
                    onValueChange = { viewModelI.CambiarInputs(nombre, precio, it) },
                    label = "Descripción"
                )
                Spacer(modifier = Modifier.height(5.dp))

                Column(
                    modifier = Modifier
                        .offset(y = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ElevatedButton(
                        onClick = { viewModelI.RegisterI(nombre, precio,descripcion,navController,usuario._id) },
                        colors = ButtonDefaults.buttonColors(Color(137, 189, 187)),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .width(330.dp)
                            .height(40.dp),
                        enabled = botonActivo
                    ) {
                        Text(
                            text = "Registrar Instrumentos",
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

            }
        }
    }

}