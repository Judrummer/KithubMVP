package com.github.judrummer.kithubmvp.app.fragment.repolist

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import com.github.judrummer.kithubmvp.R
import com.github.judrummer.kithubmvp.extension.inflate
import com.github.judrummer.kithubmvp.extension.toast
import kotlinx.android.synthetic.main.fragment_repo_list.*
import kotlinx.android.synthetic.main.item_repo.view.*
import kotlin.properties.Delegates

/**
 * Created by judrummer on 26/3/2560.
 */

class RepoListFragment : Fragment(), RepoListContract.ViewAction {

    val presenter: RepoListContract.Presenter by lazy { RepoListPresenter(this) }
    val repoListAdapter by lazy { RepoListAdapter() }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) = inflater?.inflate(R.layout.fragment_repo_list, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        repoListAdapter.onItemClick = { repo ->
            toast("Click Repo[${repo.id}] -> ${repo.name} ${repo.description}")
        }
        rvRepoList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoListAdapter
        }
    }

    override fun showRepos(repos: List<RepoListContract.RepoItem>) {
        repoListAdapter.repos = repos
    }

    override fun setProgressIndicator(active: Boolean) {

        rvRepoList.visibility = if (active) View.GONE else View.VISIBLE
        pbRepoList.visibility = if (active) View.VISIBLE else View.GONE

    }

    override fun showError(errorMessage: String) {
        toast("Error : $errorMessage")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchView = MenuItemCompat.getActionView(menu.findItem(R.id.action_search)) as SearchView
        val searchManager = context.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity.componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    presenter.searchRepos(query)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean = true

            })

        }
    }

    class RepoListAdapter : RecyclerView.Adapter<RepoListViewHolder>() {

        var repos by Delegates.observable(listOf<RepoListContract.RepoItem>()) { prop, old, new ->
            notifyDataSetChanged()
        }

        var onItemClick: ((RepoListContract.RepoItem) -> Unit)? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                RepoListViewHolder(parent.inflate(R.layout.item_repo))

        override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
            val repo = repos[position]
            holder.itemView.apply {
                tvItemStar.text = repo.starCount.toString()
                tvItemName.text = repo.name
                setOnClickListener {
                    onItemClick?.invoke(repo)
                }
            }
        }

        override fun getItemCount(): Int = repos.size

    }

    class RepoListViewHolder(view: View) : RecyclerView.ViewHolder(view)

}