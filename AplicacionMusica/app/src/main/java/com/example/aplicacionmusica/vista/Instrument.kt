package com.example.aplicacionmusica.vista

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplicacionmusica.R
import com.example.aplicacionmusica.modelo.Instrumento
import com.example.aplicacionmusica.modelo.Usuario
import com.example.aplicacionmusica.navegacion.Vistas
import com.example.aplicacionmusica.viewModel.InstrumentoVM
import com.example.aplicacionmusica.viewModel.UserVM

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Instrument(navController: NavController, viewModel: InstrumentoVM, viewModelu: UserVM) {

    val instrumento: Instrumento by viewModel.instrumento.observeAsState(Instrumento())
    val usuario: Usuario by viewModelu.user.observeAsState(Usuario(emptyList()))


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
                        "Tienda",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Vistas.AddI.ruta) }) {
                        Icon(
                            Icons.Rounded.ShoppingCart,
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
                actions = {
                    Row(horizontalArrangement = Arrangement.spacedBy(25.dp)) {
                        IconButton(onClick = { navController.navigate(Vistas.Shopping.ruta+"?id=${usuario._id}") }) {
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
                }
            )
        },
    ) { innerPadding ->

        Box(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.blanco_hueso))
            .padding(innerPadding))
        {
         Column(
             verticalArrangement = Arrangement.Center,
             horizontalAlignment = Alignment.CenterHorizontally,
         ) {
             ElevatedCard(
                 modifier = Modifier
                     .padding(5.dp)
                     .border(5.dp, colorResource(id = R.color.blanco_hueso))
                     .clip(shape = RoundedCornerShape(10))

             ) {
                 Column(
                     modifier = Modifier
                         .background(colorResource(id = R.color.main))
                         .padding(20.dp),
                     horizontalAlignment = Alignment.CenterHorizontally,
                     verticalArrangement = Arrangement.Center,
                 ) {
                     Text(
                         text =  instrumento.nombre,
                         textAlign = TextAlign.Center ,
                         modifier = Modifier.fillMaxWidth(),
                         fontFamily = FontFamily.Monospace,
                         fontSize = 30.sp,
                         color = Color.White
                     )
                    Box(
                        modifier = Modifier.padding(top=20.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .size(200.dp)
                                .border(5.dp, colorResource(id = R.color.azul_1)),
                            painter = painterResource(id = R.drawable.instrument),
                            contentDescription = "Imagen Producto"
                        )
                    }
                     Text(
                         text =  "€${instrumento.precio}",
                         textAlign = TextAlign.Center ,
                         modifier = Modifier
                             .fillMaxWidth()
                             .padding(top = 20.dp),
                         fontFamily = FontFamily.Monospace,
                         fontSize = 20.sp,
                         color = Color.White
                     )
                 }
             }
             ElevatedCard(
                 modifier = Modifier
                     .padding(15.dp)
                     .border(1.dp, colorResource(id = R.color.blanco_hueso))
                     .clip(shape = RoundedCornerShape(10))
                     .background(colorResource(id = R.color.main))
             ) {
                 Text(
                     text = "Descripcion: " ,
                     textAlign = TextAlign.Center ,
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(15.dp),
                     fontFamily = FontFamily.Monospace,
                     fontWeight = FontWeight.Bold,
                     fontSize = 25.sp
                 )
                 Text(
                     text =  instrumento.descripcion,
                     textAlign = TextAlign.Center ,
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(15.dp),
                     fontFamily = FontFamily.Monospace,
                     fontSize = 20.sp)
             }
             ElevatedCard(
                 modifier = Modifier
                     .padding(15.dp)
             ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Button(
                        onClick = {
                            navController.navigate(Vistas.Shopping.ruta+"?id=${usuario._id}")
                            viewModelu.addToCart(instrumento,navController,usuario._id)
                                  },

                        ) {
                        Text(text = "Añadir al carrito")
                    }
                }
             }
         }
        }

    }

}
