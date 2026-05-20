package com.example.midterm

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(navController: NavHostController) {

    val firebaseAuth = FirebaseAuth.getInstance()

    val offset = Offset(
        5f,
        6f
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFF1C137)
            )
            .padding(20.dp),

        horizontalAlignment =
            Alignment.CenterHorizontally,

        verticalArrangement =
            Arrangement.Center
    ) {

        Text(
            text = "PIZZERIA",

            style = TextStyle(
                fontSize = 38.sp,
                color = Color(0xFFD32C2C),
                fontWeight = FontWeight.Bold,
                letterSpacing = 9.sp,

                shadow = Shadow(
                    color = Color.Black,
                    offset = offset,
                    blurRadius = 0.5f
                )
            )
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Text(
            text =
                "Delivering\n" +
                        "Deliciousness right\n" +
                        "to your door!",

            fontSize = 32.sp,
            lineHeight = 35.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(50.dp)
        )

        Button(
            onClick = {
                // màn order
            },

            shape =
                RoundedCornerShape(35.dp),

            elevation =
                ButtonDefaults.buttonElevation(
                    defaultElevation = 15.dp
                ),

            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        Color(0xFFB90020)
                ),

            border = BorderStroke(
                1.dp,
                Color.Red
            )

        ) {

            Text(
                text = "START ORDER",
                fontWeight =
                    FontWeight.Bold,

                fontSize = 15.sp
            )
        }

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        Button(

            onClick = {

                firebaseAuth.signOut()

                navController.navigate(
                    Screen.Signin.rout
                ) {

                    popUpTo(0)
                }
            },

            shape =
                RoundedCornerShape(
                    35.dp
                ),

            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        Color(0xFFB90020)
                )

        ) {

            Text(
                text = "Sign Out",
                fontWeight =
                    FontWeight.Bold
            )
        }
    }
}