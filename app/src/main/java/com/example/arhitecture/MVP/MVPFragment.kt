package com.example.arhitecture.MVP

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.arhitecture.R
import kotlinx.android.synthetic.main.fragment_mvc.*

class MVPFragment : Fragment(R.layout.fragment_mvp), MVPView{

    private val mVPPresenter = MVPPresenterImp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVPPresenter.attachVIew(view = this@MVPFragment)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textEmail.setText("yarchike@gmai.com")
        textPassword.setText("q1w2e3r4t5y6")

        btnLogin.setOnClickListener {
            mVPPresenter.login(email = textEmail.text.toString(), password = textPassword.text.toString())
        }
    }



    override fun showSuccess() {
        Log.d("MyLogS", "Success login")
        Toast.makeText(requireContext(), "Success login", Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String) {
        Log.d("MyLogS", message)
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: Int) {
        val error = getString(message)
        Log.d("MyLogS", error)
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()


    }


}