package com.example.arhitecture.MVVM

sealed class MVVMState{
    object DefaultState: MVVMState()
    object SendingState : MVVMState()
    object LoginSucceededState : MVVMState()
    class ErrorState<T>(val message: T): MVVMState()

}
