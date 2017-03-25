package com.github.judrummer.kithubmvp.data.usecase

import com.github.judrummer.kithubmvp.data.entity.RepoEntity
import com.github.judrummer.kithubmvp.extension.responseGson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

/**
 * Created by judrummer on 26/3/2560.
 */

typealias SearchRepos = (String, (Result<SearchReposApiResponse, Exception>) -> (Unit)) -> (Unit)

data class SearchReposApiResponse(val items: List<RepoEntity>)

val SearchReposImpl: SearchRepos = { query, callback ->
    "https://api.github.com/search/repositories?q=$query".httpGet().responseGson<SearchReposApiResponse> { request, response, result ->
        callback(result)
    }
}
