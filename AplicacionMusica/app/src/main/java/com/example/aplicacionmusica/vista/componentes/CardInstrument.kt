package com.example.aplicacionmusica.vista.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplicacionmusica.R
import com.example.aplicacionmusica.navegacion.Vistas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardInstrument(titulo:String,total:String,disponibilidad:Boolean,alClickar:() -> Unit){

    ElevatedCard(
        onClick = alClickar,
        modifier = Modifier
            .padding(bottom = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(R.color.mordado_1),
        ),
    ) {
        Spacer(modifier = Modifier.size(30.dp))
        Column(
            modifier = Modifier
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                Image(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(shape = CircleShape)
                        .border(5.dp, Color.Gray, CircleShape)
                        .padding(5.dp,) ,
                    painter = painterResource(id = R.drawable.instrument),
                    contentDescription = "guitarra"
                )
            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = titulo,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = total,
                    color = Color.LightGray,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                if(disponibilidad){
                    Text(
                        text = "En stock",
                        color = Color.Green,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }else{
                    Text(
                        text = "No disponible",
                        color = Color.Red,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardInstruments(titulo:String,total:String,disponibilidad:Boolean,navController: NavController){

    var showPop by remember { mutableStateOf(false) }

    if (showPop) {
        AlertDialog(
            onDismissRequest = { showPop = false },
            title = {
                Text(

                    text = "Para ver este articulo inicia sesión",
                    textAlign = TextAlign.Center,
                    color = Color.Black

                )
                    },
            confirmButton = {
                Button(

                    onClick = {
                        navController.navigate(Vistas.Login.ruta)
                        showPop = false
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


    ElevatedCard(
        onClick = { showPop = true },
        modifier = Modifier
            .wrapContentHeight()
            .padding(bottom = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(R.color.mordado_1),
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
    ) {
        Spacer(modifier = Modifier.size(30.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                Image(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(shape = CircleShape)
                        .border(5.dp, Color.Gray, CircleShape)
                        .padding(5.dp,) ,
                    painter = painterResource(id = R.drawable.instrument),
                    contentDescription = "guitarra"
                )
            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = titulo,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = total,
                    color = Color.LightGray,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                if(disponibilidad){
                    Text(
                        text = "En stock",
                        color = Color.Green,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }else{
                    Text(
                        text = "No disponible",
                        color = Color.Red,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}
