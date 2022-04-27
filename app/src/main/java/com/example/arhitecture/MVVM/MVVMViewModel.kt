package com.example.arhitecture.MVVM

import androidx.lifecycle.MutableLiveData
import com.example.arhitecture.R
import com.example.arhitecture.repository.AuthRepositoryImpl
import com.example.arhitecture.repository.default
import com.example.arhitecture.repository.set
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MVVMViewModel {
    private var authRepository = AuthRepositoryImpl()
    val state = MutableLiveData<MVVMState>().default(MVVMState.DefaultState)

    fun login(email: String, password: String) {
        if (!validateEmail(email)) {
            state.set(MVVMState.ErrorState(message = R.string.error_email))
            return
        }
        if (!validatePassword(password)) {
            return
        }
        state.set(MVVMState.SendingState)
        CoroutineScope(Dispatchers.IO).async {
            val errorMessage = authRepository.login(
                emal = email,
                password = password
            ).await()
            if (errorMessage.isEmpty()) {
                launch(Dispatchers.Main) {
                    state.set(MVVMState.LoginSucceededState)
                }

            } else {
                launch {
                    state.set(MVVMState.ErrorState(message = errorMessage))
                }

            }
        }


    }


    //Internal logic
    private fun validatePassword(password: String): Boolean {
        return password.length > 8

    }

    private fun validateEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

}