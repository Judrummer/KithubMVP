package com.github.judrummer.kithubmvp.app.activity.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.github.judrummer.kithubmvp.R
import com.github.judrummer.kithubmvp.app.fragment.repolist.RepoListFragment
import com.github.judrummer.kithubmvp.extension.transaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportFragmentManager.transaction { add(R.id.contentContainer, RepoListFragment()) }
    }

}
