package com.e.iamfound.base

import android.content.DialogInterface
import android.view.ContextThemeWrapper
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*


open class BaseActivity : AppCompatActivity(), BaseView {
   override fun setActionBar(title: String) {

    }

    override fun setTitleBackground(colorCode: Int) {
    }

    override fun showBackButton(isVisible: Boolean) {
        if (isVisible) {
            activity_main_top_bar_layout?.activity_toolbar_back?.visibility = View.VISIBLE
            activity_main_top_bar_layout?.layout_toolbar_title?.visibility = View.VISIBLE
        } else {
            activity_main_top_bar_layout?.activity_toolbar_back?.visibility = View.GONE
            activity_main_top_bar_layout?.layout_toolbar_title?.visibility = View.VISIBLE
        }
    }
    override fun showTitle(isVisible: Boolean) {
        if (isVisible) {
            activity_main_top_bar_layout?.layout_toolbar_title?.visibility = View.VISIBLE
        } else {

            activity_main_top_bar_layout?.layout_toolbar_title?.visibility = View.GONE
        }
    }
    override fun showActionBar(isVisible: Boolean) {
        if (isVisible)
            supportActionBar?.show()
        else
            supportActionBar?.hide()
    }

    override fun showToolbar(isVisible: Boolean) {
        if (isVisible) {
         rl_toolbar?.visibility = View.VISIBLE
        } else {

            rl_toolbar.visibility = View.GONE
        }
    }


    override fun showAlert(
        message: String,
        title: String,
        okDismissClick: ((DialogInterface, Int) -> Unit?)?
    ) {

    }

    override fun showAlertWithView(view: View?): AlertDialog? {
        val alertDialogBuilder = AlertDialog.Builder(this)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.setView(view)
        alertDialog.show()
        return alertDialog
    }






    fun showProgressBar(isVisible: Boolean) {
        if (isVisible) {
            progress_bar_frame!!.visibility = View.VISIBLE
            enableUserInteraction(false)
        } else {
            progress_bar_frame!!.visibility = View.GONE
            enableUserInteraction(true)
        }

    }

    fun enableUserInteraction(enable: Boolean) {
        if (enable) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        } else {

            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        }
    }

    override fun showConfirmatonAlert(
        message: String,
        title: String,
        yesDismissClick: ((DialogInterface, Int) -> Unit?)?,
        noDismissClick: ((DialogInterface, Int) -> Unit?)?
    ) {

    }




}