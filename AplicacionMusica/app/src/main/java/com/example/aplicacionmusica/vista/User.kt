package com.example.aplicacionmusica.vista

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplicacionmusica.R
import com.example.aplicacionmusica.modelo.Usuario
import com.example.aplicacionmusica.navegacion.Vistas
import com.example.aplicacionmusica.viewModel.DelVM
import com.example.aplicacionmusica.viewModel.UserVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun User(navController: NavController, viewModel: UserVM,viewModels: DelVM){

    val user : Usuario by viewModel.user.observeAsState(Usuario(emptyList()))
    var salir by remember { mutableStateOf(false) }

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
                        "Perfil",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Vistas.Home.ruta + "?id=${user._id}") }) {
                        Icon(
                            Icons.Rounded.Home,
                            modifier = Modifier.size(30.dp),
                            contentDescription = "",
                            tint = colorResource(id = R.color.blanco_hueso)
                        )
                    }
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = colorResource(id = R.color.main),
                contentColor = colorResource(id = R.color.blanco_hueso  ),
                actions = {},
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { salir = true },
                        containerColor = Color.White,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon( Icons.Rounded.ExitToApp,modifier = Modifier.size(30.dp), contentDescription = "", tint = Color.Black)
                    }
                }
            )
        },
    ) { innerPadding ->

        Box(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.blanco_hueso))
            .padding(innerPadding))
        {
            if (salir) {
                AlertDialog(
                    containerColor = Color.White,
                    onDismissRequest = { salir = false },
                    title = {
                        Text(
                            text = "Seguro que quieres salir?",
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            textDecoration = TextDecoration.Underline
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                navController.navigate(Vistas.HomeLog.ruta)
                                salir= false
                            }
                        ) {
                            Text("Aceptar")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                salir = false
                            }
                        ) {
                            Text("Cancelar")
                        }
                    }
                )
            }
            var showPop by remember { mutableStateOf(false) }
            var confirm by remember { mutableStateOf(false) }


            if (showPop) {
                AlertDialog(
                    containerColor = Color.White,
                    onDismissRequest = { showPop = false },
                    title = {
                        Text(

                            text = "¿Seguro que quieres eliminar tu cuenta?",
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            textDecoration = TextDecoration.Underline

                        )
                    },
                    confirmButton = {
                        Button(

                            onClick = {
                                viewModels.Delate(user._id,navController)
                                showPop = false
                                confirm = true
                            }
                        ) {
                            Text("Aceptar")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                showPop = false
                            }
                        ) {
                            Text("Cancelar")
                        }
                    }
                )
            }
            if (confirm) {
                AlertDialog(
                    containerColor = Color.White,
                    onDismissRequest = { confirm = false },
                    title = {
                        Text(
                            text = "Tu cuenta ha sido eliminada",
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            textDecoration = TextDecoration.Underline
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                confirm= false
                            }
                        ) {
                            Text("Aceptar")
                        }
                    },
                )
            }

            Column {
                ElevatedCard(
                    modifier = Modifier
                        .padding(5.dp)
                        .border(5.dp, colorResource(id = R.color.blanco_hueso))
                        .clip(shape = RoundedCornerShape(10))

                ) {
                    Column(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text =  "${user.nombre} "+"${user.apellidos}",
                            textAlign = TextAlign.Center ,
                            modifier = Modifier.fillMaxWidth(),
                            fontFamily = FontFamily.Monospace,
                            fontSize = 40.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text =  "${user.correo}",
                            textAlign = TextAlign.Center ,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            fontFamily = FontFamily.Monospace,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                        Box(
                            modifier = Modifier.padding(top=10.dp)
                                .clip(shape = CircleShape)
                                .border(5.dp, Color.Gray, CircleShape)
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(200.dp),
                                painter = painterResource(id = R.drawable.user),
                                contentDescription = "Imagen Usuario"
                            )
                        }
                    }
                }
                ElevatedCard(
                    modifier = Modifier
                        .padding(5.dp)
                        .border(1.dp, colorResource(id = R.color.blanco_hueso))
                        .clip(shape = RoundedCornerShape(10))
                        .background(colorResource(id = R.color.main))
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Cambiar contraseña" ,
                            textAlign = TextAlign.Center ,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Button(onClick = {navController.navigate("${Vistas.PassCh.ruta}?id=${user._id}")}) {
                            Text(text = "Cambiar")
                        }
                    }
                }
                ElevatedCard(
                    modifier = Modifier
                        .padding(5.dp)
                        .border(1.dp, colorResource(id = R.color.blanco_hueso))
                        .clip(shape = RoundedCornerShape(10))
                        .background(colorResource(id = R.color.main))
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Eliminar cuenta" ,
                            textAlign = TextAlign.Center ,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Button(
                            onClick = { showPop = true },
                            colors = ButtonDefaults.buttonColors(Color.Red)
                        ) {
                            Text(text = "Eliminar")
                        }
                    }
                }
            }
        }
    }
}