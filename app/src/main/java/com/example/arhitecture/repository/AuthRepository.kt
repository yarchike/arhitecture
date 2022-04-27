package com.example.arhitecture.repository

import kotlinx.coroutines.Deferred

interface AuthRepository {
    suspend fun login(emal: String , password: String) : Deferred<String>
}