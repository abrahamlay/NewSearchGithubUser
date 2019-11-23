package com.abrahamlay.newsearchgithubuser.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import com.abrahamlay.data.common.applyIoScheduler
import com.abrahamlay.domain.common.ResultState
import com.abrahamlay.domain.entity.SearchResultEntity
import com.abrahamlay.newsearchgithubuser.R
import com.abrahamlay.newsearchgithubuser.ui.extention.*
import com.abrahamlay.newsearchgithubuser.ui.widget.ErrorViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Abraham Lay on 2019-11-22.
 */
class MainFragment : Fragment() {

    private val viewModel by viewModel<HomeViewModel>()

    private val adapter by lazy { MainAdapter() }

    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_main, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    @SuppressLint("CheckResult")
    private fun initView() {
        rv_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        rv_list.adapter = adapter

        adapter.albumItemClickEvent.applyIoScheduler().subscribe {
            Toast.makeText(context, it.login, Toast.LENGTH_SHORT).show()
        }

        observe(viewModel.getListData, ::showUsers)

        (activity as MainActivity).searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getList(query, 0)
                showLoading(progress_bar_view)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.getList(newText, 0)
                showLoading(progress_bar_view)
                return true
            }
        })
    }

    private fun showUsers(users: ResultState<PagedList<SearchResultEntity.ItemsItem>>) {
        when (users) {
            is ResultState.Success -> {
                hideLoading(progress_bar_view)
                hideError(ErrorViewHolder(empty_view))
                adapter.submitList(users.data)
            }
            is ResultState.Error -> {
                hideLoading(progress_bar_view)
                showError(ErrorViewHolder(empty_view), users.throwable.message)
                Toast.makeText(context, users.throwable.message, Toast.LENGTH_SHORT).show()
                adapter.submitList(users.data)
            }
            is ResultState.Loading -> {
                hideLoading(progress_bar_view)
                if(users.data.isEmpty()){
                    showError(ErrorViewHolder(empty_view), resources.getString(R.string.error_empty_query))
                }
                adapter.submitList(users.data)
            }
        }
        isLoading = false
    }
}