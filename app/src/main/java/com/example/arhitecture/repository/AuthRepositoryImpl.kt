package com.example.arhitecture.repository

import com.example.arhitecture.repository.AuthRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AuthRepositoryImpl: AuthRepository {
    override suspend fun login(emal: String, password: String): Deferred<String> {
        return GlobalScope.async {
            Thread.sleep(3000)
            ""
        }
    }
}