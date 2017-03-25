package com.github.judrummer.kithubmvp.app.fragment.repolist

/**
 * Created by judrummer on 25/3/2560.
 */

interface RepoListContract {
    interface ViewAction {
        fun showRepos(repos: List<RepoItem>)
        fun setProgressIndicator(active: Boolean)
        fun showError(errorMessage: String)
    }

    interface Presenter {
        fun searchRepos(query: String)
    }

    data class RepoItem(val id: String = "", val name: String = "", val description: String = "", val starCount: Int = 0)
}
