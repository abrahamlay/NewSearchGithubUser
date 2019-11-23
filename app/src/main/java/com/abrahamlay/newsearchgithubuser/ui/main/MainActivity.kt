package com.abrahamlay.newsearchgithubuser.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import com.abrahamlay.newsearchgithubuser.R
import com.abrahamlay.newsearchgithubuser.ui.extention.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        replaceFragment(R.id.container, MainFragment(), false)
    }

    private fun initToolbar() {
        searchView = sv_main as SearchView
        setSupportActionBar(toolbar as Toolbar)
    }

}
