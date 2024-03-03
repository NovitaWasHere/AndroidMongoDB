package com.example.aplicacionmusica.vista

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplicacionmusica.R
import com.example.aplicacionmusica.navegacion.Vistas
import com.example.aplicacionmusica.viewModel.LoginVM


@Composable
fun Login(navController: NavController, viewModel: LoginVM) {

    val correo : String by viewModel.correo.observeAsState(initial = "")
    val contra : String by viewModel.contra.observeAsState(initial = "")
    val botonActivo : Boolean by viewModel.botonActivo.observeAsState(initial = false)
    val noValido : Boolean by viewModel.noValido.observeAsState(initial = false)


    if (noValido) {
        AlertDialog(
            containerColor = Color.White,
            onDismissRequest = { },
            title = {
                Text(

                    text = "Correo o contraseña incorrectos",
                    textAlign = TextAlign.Center,
                    color = Color.Black

                )
            },
            confirmButton = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(

                        onClick = {
                            viewModel.DesactivarAlerta()
                        }
                    ) {
                        Text("Aceptar")
                    }
                }
            },
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(185, 111, 100)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Iniciar sesión",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(top = 40.dp)
                )

                Spacer(modifier = Modifier.height(25.dp))

                LoginTextField(value = correo, onValueChange = { viewModel.CambiarInputs(it, contra) }, label = "Correo", icon = null,)
                Spacer(modifier = Modifier.height(25.dp))

                LoginTextField(value = contra, onValueChange = { viewModel.CambiarInputs(correo, it) }, label = "Contraseña", esContra = true)
                Spacer(modifier = Modifier.height(25.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .offset(y = 20.dp),
                ) {
                    ElevatedButton(
                        onClick = { viewModel.IntentarInicioSesion(correo, contra, navController) },
                        colors = ButtonDefaults.buttonColors(Color(150, 175, 170)),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .width(330.dp)
                            .height(40.dp),
                        enabled = botonActivo
                    ) {
                        Text(
                            text = "Iniciar sesión",
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Row {
                        Text(
                            text = "¿No tienes una cuenta? ",
                            color = Color(150,150,150),
                        )
                        Text(
                            text = "Regístrate",
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.clickable {
                                navController.navigate(Vistas.Register.ruta)
                            }
                        )
                    }
                }
            }
        }
        Button(
            onClick = { navController.navigate(Vistas.HomeLog.ruta) },
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = ""
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
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
            trailingIcon = {
                if (icon != null) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                    )
                }
            },
            visualTransformation = if (esContra) PasswordVisualTransformation() else VisualTransformation.None,
        )
    }
}