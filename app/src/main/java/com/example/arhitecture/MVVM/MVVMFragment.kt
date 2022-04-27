package com.example.arhitecture.MVVM

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.arhitecture.R
import kotlinx.android.synthetic.main.fragment_mvc.*

class MVVMFragment : Fragment(R.layout.fragment_mvvm) {
    private val viewModel = MVVMViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textEmail.setText("yarchike@gmai.com")
        textPassword.setText("q1w2e3r4t5y6")

        btnLogin.setOnClickListener { viewModel.login(textEmail.text.toString(), textPassword.text.toString()) }


        viewModel.state.observe(viewLifecycleOwner
        ) { state ->
            when (state) {
                MVVMState.LoginSucceededState -> {
                    Log.d("MyLogS", "Success login")
                    Toast.makeText(requireContext(), "Success login", Toast.LENGTH_SHORT).show()
                }
                MVVMState.SendingState -> {
                    Log.d("MyLogS", "SendingState")
                    textEmail.isEnabled = false
                    textPassword.isEnabled = false
                    btnLogin.isEnabled = false
                }
                is MVVMState.ErrorState<*> -> {
                    when(state.message){
                        is Int -> Toast.makeText(requireContext(), getString(state.message as Int), Toast.LENGTH_SHORT).show()
                        is String -> Toast.makeText(requireContext(), (state.message as String), Toast.LENGTH_SHORT).show()
                    }
                    textEmail.isEnabled = true
                    textPassword.isEnabled = true
                    btnLogin.isEnabled = true
                }
                MVVMState.DefaultState -> {
                    textEmail.isEnabled = true
                    textPassword.isEnabled = true
                    btnLogin.isEnabled = true
                }


            }

        }
    }
}