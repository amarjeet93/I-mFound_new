package com.e.iamfound.utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.e.iamfound.R
import kotlinx.android.synthetic.main.custom_toast.view.*


class CommonCode {

    companion object {


        fun setToast(context: Context, message: String) {
            Toast(context).also {
                // View and duration has to be setP
                val view1 = LayoutInflater.from(context).inflate(R.layout.custom_toast, null)
                it.view = view1
                it.duration = Toast.LENGTH_LONG
                // it.setText(message)
                view1.toast_text.text = message
                it.show()
            }
        }
    }
}