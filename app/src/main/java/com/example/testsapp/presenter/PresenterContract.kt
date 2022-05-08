package com.example.testsapp.presenter

import com.example.testsapp.view.ViewContract

internal interface PresenterContract {

    fun onAttach(view: ViewContract) {}

    fun onDetach() {}
}
