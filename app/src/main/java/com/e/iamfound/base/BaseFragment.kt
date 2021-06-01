package com.e.iamfound.base

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import androidx.fragment.app.Fragment
import com.e.iamfound.R
import com.e.iamfound.ui.home.main.HomeActivity
import com.e.iamfound.ui.welcome.splash.InitialActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*


abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {

    open lateinit var binding: DB
    var builder: AlertDialog.Builder? = null
    var builderTemp: AlertDialog? = null
    var builderProcessing: AlertDialog.Builder? = null
    var builderTempProcessing: AlertDialog? = null

    // private lateinit var activityRef: WeakReference<InitialActivity>
//    private lateinit var activityRefHome: WeakReference<HomeActivity>

    @LayoutRes
    abstract fun getLayoutRes(): Int

    private fun init(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init(inflater, container)
        super.onCreateView(inflater, container, savedInstanceState)
        builder = AlertDialog.Builder(activity)
        binding.lifecycleOwner = this
        return binding.root
    }
    fun showAlertWithClose(
        message: String,
        title: String,
        okDismissClick: ((DialogInterface, Int) -> Unit?)?
    ) {
        val alertDialog = androidx.appcompat.app.AlertDialog.Builder(
            ContextThemeWrapper(
                activity,
                R.style.myDialog
            )
        )
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)

        okDismissClick.let {
            alertDialog.setNeutralButton(
                "Ok",
                DialogInterface.OnClickListener(okDismissClick as (DialogInterface, Int) -> Unit)
            )
        }
        alertDialog.show()
    }
     fun showToolbar(isVisible: Boolean) {
        if (isVisible) {
            rl_toolbar?.visibility = View.VISIBLE
        } else {

            rl_toolbar.visibility = View.GONE
        }
    }
    fun showAlertConfirmationDialog(
        title: String,
        message: String,
        yesDismissClick: ((DialogInterface, Int) -> Unit?)?
    ) {
        dismissAlert()
        builder?.setMessage(message)
            ?.setTitle(title)
            ?.setPositiveButton(
                "YES",
                DialogInterface.OnClickListener(yesDismissClick as (DialogInterface, Int) -> Unit)
            )
            ?.setNegativeButton("No") { dialog, _ -> dialog.cancel() }

        builderTemp = (builder as AlertDialog.Builder).create()
        builderTemp?.setCanceledOnTouchOutside(false)
        builderTemp?.setCancelable(false)
        builderTemp?.show()
    }

    fun showErrorAlert(title: String, message: String) {
        dismissAlert()
        builder?.setMessage(message)
            ?.setTitle(title)
            ?.setNeutralButton("OK",
                { dialog, which -> dialog.cancel() })
        builderTemp = (builder as AlertDialog.Builder).create()
        builderTemp?.setCanceledOnTouchOutside(false)
        builderTemp?.setCancelable(false)
        builderTemp?.show()
    }



    fun dismissAlert() {
        builderTemp?.dismiss()
    }

    fun enableUserInteraction(enable: Boolean) {
        val activity: Activity? = activity
        if (enable) {
            if (activity != null) {
                requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }
        } else
            if (activity != null)
                requireActivity().window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )
    }

    fun setActionBar(title: String) {
        when (activity) {
            is InitialActivity -> {
                (activity as InitialActivity).setActionBar(title)
            }
            is HomeActivity -> {
                (activity as HomeActivity).setActionBar(title)
            }
        }
    }

    fun showBackButton(visibility: Boolean) {
        when (activity) {
            is InitialActivity -> {
                (activity as InitialActivity).showBackButton(visibility)
            }
            is HomeActivity -> {
                (activity as HomeActivity).showBackButton(visibility)
            }

        }
    }
    fun showTitle(visibility: Boolean) {
        when (activity) {
            is InitialActivity -> {
                (activity as InitialActivity).showTitle(visibility)
            }
            is HomeActivity -> {
                (activity as HomeActivity).showTitle(visibility)
            }

        }
    }

    fun changeBackButtonColor(boolean: Boolean) {
        when (activity) {
            is InitialActivity -> {
                if (boolean) {
//                    (activity as InitialActivity).layout_toolbar_title.setTextColor(
//                        ContextCompat.getColor(MantiflyApp.appContext!!, R.color.pink)
//                    )
                }
//                (activity as InitialActivity).activity_toolbar_back.setColorFilter(
//                    ContextCompat.getColor(MantiflyApp.appContext!!, R.color.pink)
//                )
            }
        }
    }



    fun setTitleBackground(codeCode: Int) {
        when (activity) {
//            is HomeActivity -> {
//                (activity as HomeActivity).setTitleBackground(codeCode)
//            }
            is InitialActivity -> {
                (activity as InitialActivity).setTitleBackground(codeCode)
            }

        }
    }


    fun showActionBar(visibility: Boolean) {
        when (activity) {
            is InitialActivity -> {
                (activity as InitialActivity).showActionBar(visibility)
            }
//            is HomeActivity -> {
//                (activity as HomeActivity).showActionBar(visibility)
//            }
        }
    }



    fun showProgressBar(visibility: Boolean) {
        when (activity) {
            is HomeActivity -> {
                hideKeyboard(requireView())
                (activity as HomeActivity).showProgressBar(visibility)

            }
            is InitialActivity -> {
                hideKeyboard(requireView())
                (activity as InitialActivity).showProgressBar(visibility)

            }
        }
    }




    fun showEditIcon(visibility: Boolean) {

        when (activity) {
//
//            is InitialActivity -> {
//                (activity as InitialActivity).showEditIcon(false)
//            }
//            is HomeActivity -> {
//
//                (activity as HomeActivity).showEditIcon(visibility)
//            }
        }
    }


    fun hideKeyboard(view: View) {
        when (activity) {
            is InitialActivity -> {
                val imm =
                    (activity as InitialActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            is HomeActivity -> {

                val imm =
                    (activity as HomeActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

    fun showKeyboard() {
        when (activity) {
            is InitialActivity -> {
                val imm =
                    (activity as InitialActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
//            is HomeActivity -> {
//
//                val imm =
//                    (activity as HomeActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
//            }
        }
    }

}
