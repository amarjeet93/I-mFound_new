package com.e.iamfound.ui.home.main

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.e.iamfound.R
import com.e.iamfound.base.BaseFragment
import com.e.iamfound.databinding.HomeFragmentBinding

class HomeFragment : BaseFragment<HomeFragmentBinding>(), View.OnClickListener {
    override fun getLayoutRes() = R.layout.home_fragment
    var name: String? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.homeFragment = this


        clickListener()

        showTitle(true)
        showBackButton(false)
    }

    fun clickListener() {
        binding.rlAbout.setOnClickListener(this)
        binding.rlSub.setOnClickListener(this)
        binding.rlCam.setOnClickListener(this)
        binding.rlPrivacy.setOnClickListener(this)
        binding.rlTerm.setOnClickListener(this)
        binding.rlHow.setOnClickListener(this)
        binding.tvGoProfile.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.rl_sub -> {
                showAlertWithClose(
                    getString(R.string.under_dev),
                    "Alert",
                    okDialogClick
                )
            }
            R.id.rl_cam -> {

                showAlertWithClose(
                    getString(R.string.under_dev),
                    "Alert",
                    okDialogClick
                )

            }
            R.id.rl_about -> {
                showAlertWithClose(
                    getString(R.string.under_dev),
                    "Alert",
                    okDialogClick
                )
            }
            R.id.rl_how -> {
                showAlertWithClose(
                    getString(R.string.under_dev),
                    "Alert",
                    okDialogClick
                )
            }
            R.id.rl_privacy -> {
                showAlertWithClose(
                    getString(R.string.under_dev),
                    "Alert",
                    okDialogClick
                )
            }
            R.id.rl_term -> {
                showAlertWithClose(
                    getString(R.string.under_dev),
                    "Alert",
                    okDialogClick
                )
            }
            R.id.tv_goProfile -> {
                showAlertWithClose(
                    getString(R.string.under_dev),
                    "Alert",
                    okDialogClick
                )
            }
        }
    }

    private val okDialogClick = { dialog: DialogInterface, which: Int ->
        dialog.dismiss()
    }
}