package com.github.judrummer.kithubmvp.app.fragment.repolist

import com.github.judrummer.kithubmvp.data.usecase.SearchRepos
import com.github.judrummer.kithubmvp.data.usecase.SearchReposImpl
import com.github.kittinunf.result.Result

/**
 * Created by judrummer on 26/3/2560.
 */

class RepoListPresenter(val viewAction: RepoListContract.ViewAction,
                        val searchRepos: SearchRepos = SearchReposImpl) : RepoListContract.Presenter {

    override fun searchRepos(query: String) {
        viewAction.setProgressIndicator(true)
        searchRepos(query) { result ->
            viewAction.setProgressIndicator(false)
            when (result) {
                is Result.Success -> {
                    viewAction.showRepos(result.value.items.map(::mapRepoToRepoItem))
                }
                is Result.Failure -> {
                    viewAction.showError(result.error.message ?: "unknown")
                }
            }
        }
    }

}