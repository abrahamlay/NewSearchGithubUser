package com.abrahamlay.newsearchgithubuser.ui.widget

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.abrahamlay.newsearchgithubuser.R

/**
 * Created by Abraham Lay on 2019-11-23.
 */

class ErrorViewHolder(val errorView: View) {

    var icon: ImageView = errorView.findViewById(R.id.iv_empty)
    var tvMessage: TextView = errorView.findViewById(R.id.tv_message)

    fun setMessage(message: String?){
        tvMessage.text = message
    }
}