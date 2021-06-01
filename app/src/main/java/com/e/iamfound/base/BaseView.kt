package com.e.iamfound.base

import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog

interface BaseView {
    fun setActionBar(title: String)
    fun setTitleBackground(colorCode: Int)
    fun showBackButton(isVisible: Boolean)
    fun showActionBar(isVisible: Boolean)
    fun showToolbar(isVisible: Boolean)
    fun showTitle(isVisible: Boolean)
    fun showAlert(message: String, title: String, okDismissClick: ((DialogInterface, Int) -> Unit?)?)
    fun showConfirmatonAlert(message: String, title: String, yesDismissClick: ((DialogInterface, Int) -> Unit?)?,noDismissClick: ((DialogInterface, Int) -> Unit?)?)
    fun showAlertWithView(view: View?): AlertDialog?


}