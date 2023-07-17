package com.hvk.app.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {
    private var _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private var _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    fun changePassword(password: String) {
        _password.value = password
    }

    fun changeEmail(email: String) {
        _email.value = email
    }

    val errorMessage: String = "Error "
    val isLoginError: Boolean by mutableStateOf(false)
}

