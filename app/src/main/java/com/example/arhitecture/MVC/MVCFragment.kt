package com.example.arhitecture.MVC

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.arhitecture.R
import com.example.arhitecture.repository.AuthRepositoryImpl
import kotlinx.android.synthetic.main.fragment_mvc.*
import kotlinx.coroutines.*

class MVCFragment : Fragment(R.layout.fragment_mvc) {


    private var authRepository = AuthRepositoryImpl()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textEmail.setText("yarchike@gmai.com")
        textPassword.setText("q1w2e3r4t5y6")

        btnLogin.setOnClickListener {
            if (!validateEmail()) return@setOnClickListener
            if (!validatePassword()) return@setOnClickListener
            performLogin()
        }

    }

    private fun validatePassword(): Boolean {
        return textPassword.text.toString().length > 8

    }

    private fun validateEmail(): Boolean {
        return textEmail.text.toString().contains("@") && textEmail.text.toString().contains(".")
    }

    // Model implementation
    fun performLogin() {
        lifecycleScope.launch {
            var errorMessage = authRepository.login(
                emal = textEmail.text.toString(),
                password = textPassword.text.toString()
            ).await()
            if (errorMessage.isEmpty()) {
                Log.d("MyLogS", "Success login")
                Toast.makeText(requireContext(), "Success login", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("MyLogS", errorMessage)
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()

            }
        }
    }
}