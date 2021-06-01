package com.e.iamfound.ui.welcome.signup


import android.os.Bundle
import android.text.Html
import android.util.Patterns
import android.view.View
import androidx.navigation.fragment.findNavController
import com.e.iamfound.R
import com.e.iamfound.base.BaseFragment
import com.e.iamfound.utils.*
import com.e.iamfound.databinding.FragmentSignupBinding



class RegisterFragment : BaseFragment<FragmentSignupBinding>() , View.OnClickListener {
    override fun getLayoutRes() = R.layout.fragment_signup

    var register_here: String? = null
    var deviceToken = "12345678"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.registerFragment = this

        register_here = getString(R.string.already_account)
        binding.tvLoginHere.text = Html.fromHtml(register_here)
        showBackButton(false)
        showTitle(false)


        binding.tvTerms.setOnClickListener(this)
        binding.tvPrivacy.setOnClickListener(this)
        binding.tvLoginHere.setOnClickListener(this)
        binding.tvRegister.setOnClickListener(this)
    }


    fun goToOtp() {



        this.findNavController()
            .navigate(R.id.action_signup_to_otpFragment)
        requireActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_terms -> {
                openWebScreen("Terms & Conditiom")
            }
            R.id.tv_privacy -> {
                openWebScreen("Privacy Policy")
            }
            R.id.tv_login_here->
            {
                this.findNavController()
                    .navigate(R.id.action_register_to_signupLoginFragment)
                requireActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout)
            }
            R.id.tv_register->
            {
                if (binding.etPhone.text.isBlank() ||
                    binding.etPassword.text.isBlank() ||
                    binding.etCpassword.text.isBlank()
                ) {
                    CommonCode.setToast(requireContext(), "Oops, we need more info.")
                } else if (!binding.etPassword.text.toString()
                        .equals(binding.etCpassword.text.toString())
                ) {
                    CommonCode.setToast(requireContext(), "Oops, Password not matched.")
                } else {
                    val validemail =
                        Patterns.EMAIL_ADDRESS.matcher(
                            binding.etEmail.text.toString().trim()
                        )
                            .matches()
                    if (validemail) {
                        if (binding.cbTerm.isChecked) {
                           goToOtp()
                        } else {
                            CommonCode.setToast(
                                requireContext(),
                                "Please agree to Terms & Condition and Privacy Policy"
                            )
                        }
                    } else {
                        CommonCode.setToast(requireContext(), "Oops, invalid email.")
                    }
                }
            }
        }
    }

    fun openWebScreen(title: String) {

        this.findNavController()
            .navigate(R.id.action_registe_to_webFragment)
        requireActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout)

    }
}