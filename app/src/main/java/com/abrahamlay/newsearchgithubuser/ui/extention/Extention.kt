package com.abrahamlay.newsearchgithubuser.ui.extention

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.abrahamlay.newsearchgithubuser.ui.widget.ErrorViewHolder

/**
 * Created by Abraham Lay on 2019-11-22.
 */

fun AppCompatActivity.replaceFragment(containerRes: Int,fragment :Fragment, addToBackStack: Boolean) {
    val ft = supportFragmentManager.beginTransaction()
    ft.setCustomAnimations(
        android.R.anim.fade_in,
        android.R.anim.fade_out,
        android.R.anim.fade_in,
        android.R.anim.fade_out
    )
        .replace(containerRes, fragment)

    if (addToBackStack) ft.addToBackStack(null)

    ft.commit()
}

fun Fragment.showLoading(progressDialog: View){
    progressDialog.visibility = View.VISIBLE
}

fun Fragment.hideLoading(progressDialog: View){
    progressDialog.visibility = View.GONE
}

fun Fragment.showError(errorView: ErrorViewHolder, message: String?){
    errorView.errorView.visibility = View.VISIBLE
    errorView.setMessage(message)
}

fun Fragment.hideError(errorView: ErrorViewHolder){
    errorView.errorView.visibility = View.GONE
}