package com.example.arhitecture.MVP


import com.example.arhitecture.repository.AuthRepositoryImpl
import com.example.arhitecture.R
import kotlinx.coroutines.*
import java.lang.ref.WeakReference

class MVPPresenterImp: MVPPresenter {
    private var authRepository = AuthRepositoryImpl()
    private var viewState: WeakReference<MVPView>? = null
    fun attachVIew(view: MVPView){
        viewState =  WeakReference(view)
    }

    override fun login(email: String, password: String) {
        if (!validateEmail(email)) {
            viewState?.get()?.showError(R.string.error_email)
            return
        }
        if (!validatePassword(password)) {
            return
        }

        CoroutineScope(Dispatchers.IO).async {
            val errorMessage = authRepository.login(
                emal = email,
                password = password
            ).await()
            if (errorMessage.isEmpty()) {
                launch(Dispatchers.Main) {
                    viewState?.get()?.showSuccess()
                }

            } else {
                launch {
                    viewState?.get()?.showError("Error Login")
                }

            }
        }
    }
    //Internal Logic
    private fun validatePassword(password: String): Boolean {
        return password.length > 8

    }

    private fun validateEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

}