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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplicacionmusica.R
import com.example.aplicacionmusica.viewModel.RegisterVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(navController: NavController, viewModel: RegisterVM) {
    val nombre: String by viewModel.nombre.observeAsState(initial = "")
    val apellidos: String by viewModel.apellidos.observeAsState(initial = "")
    val contrasena: String by viewModel.contrasena.observeAsState(initial = "")
    val correo: String by viewModel.correo.observeAsState(initial = "")
    val botonActivo: Boolean by viewModel.botonActivo.observeAsState(initial = false)

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
            text = "Regístrate",
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(20.dp))

        RegistroTextField(
            value = nombre,
            onValueChange = { viewModel.CambiarInputs(it, apellidos, contrasena, correo) },
            label = "Nombre"
        )
        Spacer(modifier = Modifier.height(5.dp))

        RegistroTextField(
            value = apellidos,
            onValueChange = { viewModel.CambiarInputs(nombre, it, contrasena, correo) },
            label = "Apellidos"
        )
        Spacer(modifier = Modifier.height(5.dp))

        RegistroTextField(
            value = correo,
            onValueChange = { viewModel.CambiarInputs(nombre, apellidos, contrasena, it) },
            label = "Correo electrónico"
        )
        Spacer(modifier = Modifier.height(5.dp))

        RegistroTextField(
            value = contrasena,
            onValueChange = { viewModel.CambiarInputs(nombre, apellidos, it, correo) },
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
                onClick = { viewModel.Register(nombre, apellidos,contrasena, correo, navController) },
                colors = ButtonDefaults.buttonColors(Color(137, 189, 187)),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .width(330.dp)
                    .height(40.dp),
                enabled = botonActivo
            ) {
                Text(
                    text = "Registrar",
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Text(
                    text = "¿Ya tienes una cuenta? ",
                    color = Color(150,150,150),
                )
                Text(
                    text = "Inicia sesión",
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable {
                        navController.navigate("Login")
                    }
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroTextField(
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
