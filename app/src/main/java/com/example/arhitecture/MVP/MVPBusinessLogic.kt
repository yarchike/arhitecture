package com.example.arhitecture.MVP

import android.provider.ContactsContract

interface MVPPresenter{
    fun login(email: String, password: String)
}
interface MVPView{
    fun showSuccess()
    fun showError(message: String)
    fun showError(message: Int)


}