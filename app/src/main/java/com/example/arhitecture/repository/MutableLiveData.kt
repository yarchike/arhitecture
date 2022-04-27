package com.example.arhitecture.repository

import androidx.lifecycle.MutableLiveData

fun  <T: Any?>MutableLiveData<T>.default(initValue: T) = apply { value = initValue }

fun  <T>MutableLiveData<T>.set(newValue: T) = apply { value = newValue }