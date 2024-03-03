package com.example.aplicacionmusica.vista

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
import com.example.aplicacionmusica.viewModel.UserVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoInstrumento(navController: NavController, viewModelu: UserVM){

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val usuario: Usuario by viewModelu.user.observeAsState(Usuario(emptyList()))
    val instrumentos:List<Instrumento> = usuario.instrumentos

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

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
                        "Tu carrito ${usuario.apellidos}",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("${Vistas.Home.ruta}?id=${usuario._id}")}) {
                        Icon(
                            Icons.Rounded.Home,
                            modifier = Modifier.size(30.dp),
                            contentDescription = "",
                            tint = colorResource(id = R.color.blanco_hueso)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = colorResource(id = R.color.main),
                contentColor = colorResource(id = R.color.blanco_hueso  ),
                actions = {
                    Row(horizontalArrangement = Arrangement.spacedBy(25.dp)) {
                        IconButton(onClick = {
                            Log.d("ID DEL USUARIO",usuario._id)
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
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.blanco_hueso))
            .padding(innerPadding))
        {
            LazyColumn(modifier = Modifier
                .padding(top = 15.dp, bottom = 5.dp, start = 15.dp, end = 15.dp)
                .fillMaxSize(),
                content = {
                    items(instrumentos.size) { x ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                         ElevatedCard(
                             modifier = Modifier
                                 .padding(5.dp).width(200.dp).wrapContentHeight()
                         ) {
                             Column (
                                 verticalArrangement = Arrangement.Center,
                                 horizontalAlignment = Alignment.CenterHorizontally,
                                 modifier = Modifier.padding(15.dp)
                             ){
                                 Text(
                                     text = instrumentos[x].nombre,
                                     fontWeight = FontWeight.Bold,
                                     fontSize = 30.sp,
                                     textAlign = TextAlign.Center,
                                     fontFamily = FontFamily.Cursive,
                                 )
                                 Image(
                                     painter = painterResource(id = R.drawable.instrument)
                                     , contentDescription = "Imagen producto",
                                     modifier = Modifier.size(50.dp))
                                 Text(
                                     text = instrumentos[x].precio.toString(),
                                     fontWeight = FontWeight.Bold,
                                     fontSize = 18.sp,
                                     textAlign = TextAlign.Center,
                                 )
                                 if(instrumentos[x].disponibilidad){

                                     Text(text = "Disponible",
                                         fontWeight = FontWeight.Bold,
                                         fontSize = 15.sp,
                                         textAlign = TextAlign.Center,
                                         color = Color.Green,)

                                 }else{

                                     Text(text = "No disponible",
                                         fontWeight = FontWeight.Bold,
                                         fontSize = 15.sp,
                                         textAlign = TextAlign.Center,
                                         color= Color.Red,)

                                 }
                                 Text(
                                     text = instrumentos[x].descripcion,
                                     fontWeight = FontWeight.Bold,
                                     fontSize = 12.sp,
                                     textAlign = TextAlign.Center,
                                     fontFamily = FontFamily.Serif,
                                 )
                             }

                         }
                        }
                    }
                }
            )
        }
    }
}