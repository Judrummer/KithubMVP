package com.github.judrummer.kithubmvp.data.entity

/**
 * Created by judrummer on 19/3/2560.
 */

data class RepoEntity(val id: String = "",
                      val name: String = "",
                      val description: String? = "",
                      val full_name: String = "",
                      val stargazers_count: Int = 0)
