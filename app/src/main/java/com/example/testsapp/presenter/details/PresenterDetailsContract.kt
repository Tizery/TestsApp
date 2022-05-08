package com.example.testsapp.presenter.details

import com.example.testsapp.presenter.PresenterContract

internal interface PresenterDetailsContract : PresenterContract {
    fun setCounter(count: Int)
    fun onIncrement()
    fun onDecrement()
}
