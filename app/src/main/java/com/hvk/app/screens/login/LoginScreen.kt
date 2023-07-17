package com.hvk.app.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.hvk.app.R
import com.hvk.app.components.BottomMessageScreen
import com.hvk.app.screens.home.DashboardScreen
import org.koin.androidx.compose.getViewModel

class LoginScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        val viewModel: LoginViewModel = getViewModel()

        val email by viewModel.email.collectAsStateWithLifecycle()

        val password by viewModel.password.collectAsStateWithLifecycle()

        LaunchedEffect(viewModel.isLoginError) {
            bottomSheetNavigator.show(BottomMessageScreen(viewModel.errorMessage))
        }

        val navigation = LocalNavigator.currentOrThrow
        ContentUI(
            password = password,
            passwordChange = viewModel::changePassword,
            email = email,
            emailChange = viewModel::changeEmail
        ) {
            navigation.push(DashboardScreen())
        }

    }

    @Composable
    fun ContentUI(
        password: String,
        passwordChange: (String) -> Unit,
        email: String,
        emailChange: (String) -> Unit,
        loginPress: () -> Unit
    ) {
        Scaffold {
            println(it)
            Column(
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {

                Spacer(modifier = Modifier.weight(1f))
                Image(painterResource(id = R.drawable.user), contentDescription = "logo")
                Spacer(modifier = Modifier.height(56.dp))
                TextField(
                    value = email,
                    onValueChange = { text ->
                        emailChange(text)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Email") },
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = password,
                    onValueChange = { text ->
                        passwordChange(text)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Password") },
                    trailingIcon = { Icon(Icons.Default.Lock, contentDescription = "password") }
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = loginPress,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Login")
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLoginScreen() {
    LoginScreen().ContentUI(password = "", passwordChange = {}, email = "", emailChange = {}) {

    }
}