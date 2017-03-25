package com.github.judrummer.kithubmvp.app.fragment.repolist

import com.github.judrummer.kithubmvp.data.entity.RepoEntity

/**
 * Created by judrummer on 26/3/2560.
 */

fun mapRepoToRepoItem(repo: RepoEntity) = RepoListContract.RepoItem(
        repo.id,
        repo.name,
        repo.description ?: "",
        repo.stargazers_count)