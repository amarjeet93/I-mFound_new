package com.e.iamfound.ui.welcome.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.e.iamfound.R
import com.e.iamfound.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class InitialActivity : BaseActivity() {
    companion object {
        fun launchActivity(context: Context) {
            val intent = Intent(context, InitialActivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
        var otpCallback: Boolean? = false

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activity_toolbar_back.setOnClickListener {
            showBackButton(false)

            layout_toolbar_title.text=""
        onBackPressed()
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }
    }
    override fun setActionBar(title: String) {
        layout_toolbar_title.setTextColor(ContextCompat.getColor(this, R.color.dodgerBlue))
        activity_toolbar_back.setImageDrawable(getDrawable(R.drawable.ic_action_back))
        activity_main_top_bar_layout.layout_toolbar_title?.text = title
    }
    private fun configureToolBar() {
        setSupportActionBar(activity_main_top_bar_layout as Toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        activity_main_top_bar_layout.activity_toolbar_back.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        showBackButton(false)
        layout_toolbar_title.text=""

    }


}