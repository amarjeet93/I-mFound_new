package com.e.iamfound.ui.welcome.login


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import androidx.navigation.fragment.findNavController
import com.e.iamfound.R
import com.e.iamfound.base.BaseFragment
import com.e.iamfound.utils.CommonCode
import com.e.iamfound.databinding.FragmentLoginBinding
import com.e.iamfound.ui.home.main.HomeActivity

val sharedPrefFile = "foundsharedpreference"

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun getLayoutRes() = R.layout.fragment_login
    val PERMISSION_ID = 42
    var register_here: String? = null
    var deviceToken = "12345678"
    var sharedPreferences: SharedPreferences? = null


    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        binding.loginFragment = this
        showTitle(false)
        showBackButton(false)
        sharedPreferences = requireActivity().getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )

        register_here = getString(R.string.dont_account)
        binding.tvRegisterHere.text = Html.fromHtml(register_here)

        binding.tvLogin.setOnClickListener { gotToHome() }

        binding.tvForgot.setOnClickListener { goToForgot() }
        binding.tvRegisterHere.setOnClickListener { goToRegister() }
    }


    fun gotToHome() {

        if (binding.etPhone.text.isBlank() ||
            binding.etPassword.text.isBlank()
        ) {
 CommonCode.setToast(requireContext(), "Oops, we need more info.")
        } else {

            openNextScreen()
        }
    }





    fun openNextScreen() {

        startActivity(Intent(activity, HomeActivity::class.java))
        activity?.finish()


    }


    fun goToRegister() {

        this.findNavController()
            .navigate(R.id.action_loginFragment_to_signupLoginFragment)
        requireActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }

    fun goToForgot() {

        this.findNavController()
            .navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        requireActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }

}