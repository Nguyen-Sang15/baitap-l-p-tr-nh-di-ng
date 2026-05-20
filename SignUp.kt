package com.example.midterm

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth

@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun SignUp(navController: NavHostController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val focusEmail = remember { FocusRequester() }
    val focusPassword = remember { FocusRequester() }
    val focusConfirm = remember { FocusRequester() }

    val keyboardController =
        LocalSoftwareKeyboardController.current

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val firebaseAuth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFCF4))
            .padding(horizontal = 40.dp),

        horizontalAlignment =
            Alignment.CenterHorizontally,

        verticalArrangement =
            Arrangement.Center
    ) {

        Text(
            text = "Welcome To",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "PIZZERIA!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB90020)
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        OutlinedTextField(
            value = email,

            onValueChange = {
                email = it
            },

            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(
                    focusEmail
                ),

            label = {
                Text("Email")
            },

            singleLine = true,

            keyboardOptions =
                KeyboardOptions(
                    imeAction =
                        ImeAction.Next
                ),

            keyboardActions =
                KeyboardActions(
                    onNext = {
                        focusPassword.requestFocus()
                    }
                )
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        OutlinedTextField(
            value = password,

            onValueChange = {
                password = it
            },

            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(
                    focusPassword
                ),

            label = {
                Text("Password")
            },

            singleLine = true,

            visualTransformation =
                if (isPasswordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),

            trailingIcon = {

                IconButton(
                    onClick = {
                        isPasswordVisible =
                            !isPasswordVisible
                    }
                ) {

                    Icon(
                        imageVector =
                            if (isPasswordVisible)
                                Icons.Default.LockOpen
                            else
                                Icons.Default.Lock,

                        contentDescription = null
                    )
                }
            },

            keyboardOptions =
                KeyboardOptions(
                    keyboardType =
                        KeyboardType.Password,

                    imeAction =
                        ImeAction.Next
                ),

            keyboardActions =
                KeyboardActions(
                    onNext = {
                        focusConfirm.requestFocus()
                    }
                )
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        OutlinedTextField(
            value = confirmPassword,

            onValueChange = {
                confirmPassword = it
            },

            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(
                    focusConfirm
                ),

            label = {
                Text("Confirm Password")
            },

            singleLine = true,

            visualTransformation =
                if (isPasswordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),

            keyboardOptions =
                KeyboardOptions(
                    keyboardType =
                        KeyboardType.Password,

                    imeAction =
                        ImeAction.Done
                ),

            keyboardActions =
                KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                )
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = {

                if (
                    email.isNotEmpty() &&
                    password.isNotEmpty() &&
                    confirmPassword.isNotEmpty()
                ) {

                    if (
                        password == confirmPassword
                    ) {

                        firebaseAuth
                            .createUserWithEmailAndPassword(
                                email.trim(),
                                password
                            )
                            .addOnCompleteListener {

                                if (it.isSuccessful) {

                                    Toast.makeText(
                                        context,
                                        "Registration Successful",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    navController.navigate(
                                        Screen.Signin.rout
                                    )

                                } else {

                                    Toast.makeText(
                                        context,
                                        it.exception?.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                    } else {

                        Toast.makeText(
                            context,
                            "Passwords do not match",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } else {

                    Toast.makeText(
                        context,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),

            shape =
                RoundedCornerShape(16.dp),

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
                text = "Sign Up",
                fontSize = 20.sp
            )
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Row(
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                text =
                    "Already have an account?"
            )

            TextButton(
                onClick = {

                    navController.navigate(
                        Screen.Signin.rout
                    )
                }
            ) {

                Text(
                    text = "Sign In now!",
                    color = Color(0xFFB90020),
                    fontWeight =
                        FontWeight.Bold
                )
            }
        }
    }
}