package com.e.iamfound.ui.welcome.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.e.iamfound.R
import com.e.iamfound.base.BaseFragment
import com.e.iamfound.databinding.FragmentSplashBinding
import com.e.iamfound.ui.home.main.HomeActivity
import com.e.iamfound.ui.welcome.login.sharedPrefFile


class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    var isLoggedIn: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        showTitle(false)
goToLogin()
        return inflater.inflate(R.layout.fragment_splash, container, false)

    }


    private fun goToLogin() {
        // Permission Granted-System will work
        val handler = Handler()
        handler.postDelayed({

                    this.findNavController()
                        .navigate(R.id.action_splashFragment_to_signupLoginFragment)
                   requireActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout)

        }, 2000)

    }


    override fun getLayoutRes(): Int = R.layout.fragment_splash

}
