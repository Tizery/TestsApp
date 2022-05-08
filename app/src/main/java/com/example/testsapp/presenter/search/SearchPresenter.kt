package com.example.testsapp.presenter.search

import com.example.testsapp.model.SearchResponse
import com.example.testsapp.repository.GitHubRepository
import com.example.testsapp.repository.GitHubRepository.GitHubRepositoryCallback
import com.example.testsapp.view.ViewContract
import com.example.testsapp.view.search.ViewSearchContract
import retrofit2.Response

internal class SearchPresenter internal constructor(
    private val viewContract: ViewSearchContract,
    private val repository: GitHubRepository
) : PresenterSearchContract, GitHubRepositoryCallback {

    override fun onAttach(view: ViewContract) {
        super.onAttach(view)
    }

    override fun searchGitHub(searchQuery: String) {
        viewContract.displayLoading(true)
        repository.searchGithub(searchQuery, this)
    }

    override fun handleGitHubResponse(response: Response<SearchResponse?>?) {
        viewContract.displayLoading(false)
        if (response != null && response.isSuccessful) {
            val searchResponse = response.body()
            val searchResults = searchResponse?.searchResults
            val totalCount = searchResponse?.totalCount
            if (searchResults != null && totalCount != null) {
                viewContract.displaySearchResults(
                    searchResults,
                    totalCount
                )
            } else {
                viewContract.displayError("Search results or total count are null")
            }
        } else {
            viewContract.displayError("Response is null or unsuccessful")
        }
    }

    override fun handleGitHubError() {
        viewContract.displayLoading(false)
        viewContract.displayError()
    }

    override fun onDetach() {
        super.onDetach()
    }
}
