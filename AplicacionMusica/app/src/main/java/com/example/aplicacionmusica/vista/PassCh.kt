package com.example.aplicacionmusica.vista

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplicacionmusica.R
import com.example.aplicacionmusica.modelo.Usuario
import com.example.aplicacionmusica.viewModel.PassVM
import com.example.aplicacionmusica.viewModel.UserVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassCh(navController: NavController, viewModel: PassVM,viewModelu: UserVM) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val contrasenaA: String by viewModel.contrasenaA.observeAsState(initial = "")
    val contrasena: String by viewModel.contrasena.observeAsState(initial = "")
    val botonActivo: Boolean by viewModel.botonActivo.observeAsState(initial = false)
    val user : Usuario by viewModelu.user.observeAsState(Usuario(emptyList()))

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
                        "Hola ${user.nombre}",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(
                            Icons.Rounded.ArrowBack,
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
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                //verticalArrangement = Arrangement.Center
            ) {
                Box(
                    //contentAlignment = Alignment.TopCenter
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.16f),
                        contentScale = ContentScale.FillBounds
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Cambio contraseña",
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.height(20.dp))

                RegistroTextFields(
                    value = contrasenaA,
                    onValueChange = { viewModel.CambiarInputs(it,contrasena) },
                    label = "Contraseña actual"
                )
                Spacer(modifier = Modifier.height(5.dp))

                RegistroTextFields(
                    value = contrasena,
                    onValueChange = { viewModel.CambiarInputs(contrasenaA, it) },
                    label = "Contraseña",
                    esContra = true
                )

                Column(
                    modifier = Modifier
                        .offset(y = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ElevatedButton(
                        onClick = { viewModel.Cambiar(user.correo,contrasenaA ,contrasena,navController) },
                        colors = ButtonDefaults.buttonColors(Color(137, 189, 187)),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .width(330.dp)
                            .height(40.dp),
                        enabled = botonActivo
                    ) {
                        Text(
                            text = "Cambiar contraseña",
                            fontWeight = FontWeight.Medium
                        )
                    }

                }

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroTextFields(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    @DrawableRes icon: Int? = null,
    esContra: Boolean = false
) {
    Column {
        TextField(
            value = value,
            onValueChange = { onValueChange(it) },
            label = { Text(text = label, fontSize = 15.sp) },
            modifier = Modifier.width(330.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                unfocusedIndicatorColor = colorResource(id = R.color.azul_1),
                focusedIndicatorColor = colorResource(id = R.color.azul_1),
                focusedLabelColor = colorResource(id = R.color.azul_1),
                unfocusedLabelColor = colorResource(id = R.color.azul_1)
            ),

            visualTransformation = if (esContra) PasswordVisualTransformation() else VisualTransformation.None,
        )
    }
}