package com.e.iamfound.ui.home.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.e.iamfound.R
import com.e.iamfound.base.BaseActivity
import kotlinx.android.synthetic.main.layout_toolbar.*


class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        activity_toolbar_back.setOnClickListener {
            showBackButton(false)
            layout_toolbar_title.text=""
            onBackPressed()
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }
    }
    override fun setActionBar(title: String) {
        layout_toolbar_title?.text = title
    }

    override fun onBackPressed() {
        super.onBackPressed()
        showBackButton(false)
        layout_toolbar_title.text=""

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.fragment_container)
        fragment?.onActivityResult(requestCode, resultCode, data)
    }
}