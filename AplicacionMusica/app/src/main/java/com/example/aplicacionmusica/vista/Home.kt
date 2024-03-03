package com.example.aplicacionmusica.vista

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplicacionmusica.R
import com.example.aplicacionmusica.modelo.Instrumento
import com.example.aplicacionmusica.modelo.Usuario
import com.example.aplicacionmusica.navegacion.Vistas
import com.example.aplicacionmusica.viewModel.ListadoInstrumentosVM
import com.example.aplicacionmusica.viewModel.UserVM
import com.example.aplicacionmusica.vista.componentes.CardInstrument
import com.example.aplicacionmusica.vista.componentes.CardInstruments


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController, viewModel: ListadoInstrumentosVM, viewModelu: UserVM){

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        val instrumentos: List<Instrumento> by viewModel.instrumentos.observeAsState(emptyList())
        val usuario: Usuario by viewModelu.user.observeAsState(Usuario(emptyList()))

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
                            "Hola ${usuario.nombre}",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.SemiBold,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate(Vistas.Shopping.ruta+"?id=${usuario._id}" )}) {
                            Icon(
                                Icons.Rounded.ShoppingCart,
                                modifier = Modifier.size(30.dp),
                                contentDescription = "",
                                tint = colorResource(id = R.color.blanco_hueso)
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                Log.d("ID DEL USUARIO",usuario._id)
                                navController.navigate("${Vistas.AddI.ruta}?id=${usuario._id}")
                                }
                        )
                        {
                            Icon(
                                Icons.Rounded.AddCircle,
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
                                navController.navigate(Vistas.User.ruta + "?id=${usuario._id}"
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
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 15.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CardInstrument(
                                    instrumentos[x].nombre,
                                    "${instrumentos[x].precio} €",
                                    instrumentos[x].disponibilidad,
                                    alClickar = {
                                        navController.navigate(Vistas.Instrument.ruta + "?id=" + instrumentos[x]._id + "&idu=${usuario._id}")
                                    })
                            }
                        }
                    }
                )
            }
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeLog(navController: NavController, viewModel: ListadoInstrumentosVM){

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val instrumentos: List<Instrumento> by viewModel.instrumentos.observeAsState(emptyList())


    LaunchedEffect(Unit) {

        viewModel.getInstrumentos()

    }

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
                        "Tienda",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Vistas.Login.ruta)}) {
                        Icon(
                            Icons.Rounded.Person,
                            modifier = Modifier.size(30.dp),
                            contentDescription = "",
                            tint = colorResource(id = R.color.blanco_hueso)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
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
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CardInstruments(instrumentos[x].nombre, "${instrumentos[x].precio} €",instrumentos[x].disponibilidad,navController)
                        }
                    }
                }
            )
        }
    }
}