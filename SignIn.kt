package com.example.midterm

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.navigation.NavHostController
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun SignIn(navController: NavHostController) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    val (focusUsername, focusPassword) =
        remember { FocusRequester.createRefs() }

    val keyboardController =
        LocalSoftwareKeyboardController.current

    val context = LocalContext.current
    val firebaseAuth = FirebaseAuth.getInstance()

    val scope = rememberCoroutineScope()

    val credentialManager =
        CredentialManager.create(context)

    val webClientId =
        "466031615447-8b1s9u22ip87s3vb9je9lbn1elkojjni.apps.googleusercontent.com"

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
            text = "Welcome Back",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "PIZZERIA",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        OutlinedTextField(
            value = username,

            onValueChange = {
                username = it
            },

            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(
                    focusUsername
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
            modifier = Modifier.height(12.dp)
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
                    username.trim().isNotEmpty()
                    &&
                    password.isNotEmpty()
                ) {

                    firebaseAuth
                        .signInWithEmailAndPassword(
                            username.trim(),
                            password
                        )
                        .addOnCompleteListener {

                            if (it.isSuccessful) {

                                navController.navigate(
                                    Screen.Home.rout
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
                        "Không được để trống",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            },

            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),

            shape =
                RoundedCornerShape(15.dp)
        ) {

            Text(
                text = "Sign In",
                fontSize = 20.sp
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Row(
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                text =
                    "Is it first for you?"
            )

            TextButton(
                onClick = {

                    navController.navigate(
                        Screen.Signup.rout
                    )

                }
            ) {

                Text(
                    text =
                        " Sign Up now!",

                    color =
                        Color.Red
                )
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "OR Sign In with",
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        Box(
            modifier = Modifier
                .size(50.dp)
                .border(
                    BorderStroke(
                        1.dp,
                        Color.Gray
                    ),
                    CircleShape
                )

                .clickable {

                    val googleIdOption =
                        GetGoogleIdOption.Builder()
                            .setFilterByAuthorizedAccounts(false)
                            .setServerClientId(
                                webClientId
                            )
                            .build()

                    val request =
                        GetCredentialRequest.Builder()
                            .addCredentialOption(
                                googleIdOption
                            )
                            .build()

                    scope.launch {

                        try {

                            val result =
                                credentialManager
                                    .getCredential(
                                        context,
                                        request
                                    )

                            val credential =
                                result.credential

                            if (
                                credential
                                        is GoogleIdTokenCredential
                            ) {

                                val firebaseCredential =
                                    GoogleAuthProvider
                                        .getCredential(
                                            credential.idToken,
                                            null
                                        )

                                firebaseAuth
                                    .signInWithCredential(
                                        firebaseCredential
                                    )

                                    .addOnCompleteListener {

                                        if (it.isSuccessful) {

                                            navController.navigate(
                                                Screen.Home.rout
                                            )
                                        }
                                    }
                            }

                        } catch (e: Exception) {

                            Toast.makeText(
                                context,
                                e.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                },

            contentAlignment =
                Alignment.Center
        ) {

            Text(
                text = "G",
                color = Color.Red,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}