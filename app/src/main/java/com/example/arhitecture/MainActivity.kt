package com.example.arhitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.arhitecture.MVC.MVCFragment
import com.example.arhitecture.MVP.MVPFragment
import com.example.arhitecture.MVVM.MVVMFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MVVMFragment())
            .commit()
    }
}