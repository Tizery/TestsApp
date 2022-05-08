package com.example.testsapp.presenter.search

import com.example.testsapp.presenter.PresenterContract

internal interface PresenterSearchContract : PresenterContract {
    fun searchGitHub(searchQuery: String)
    //onAttach
    //onDetach
}
