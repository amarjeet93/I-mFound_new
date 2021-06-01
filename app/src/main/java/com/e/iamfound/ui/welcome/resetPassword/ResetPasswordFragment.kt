package com.e.iamfound.ui.welcome.resetPassword

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.e.iamfound.R
import com.e.iamfound.base.BaseFragment
import com.e.iamfound.utils.CommonCode
import com.e.iamfound.databinding.ResetPasswordFragmentBinding


class ResetPasswordFragment : BaseFragment<ResetPasswordFragmentBinding>() {
    override fun getLayoutRes() = R.layout.reset_password_fragment

    var mobileNumber: String? = null

    var password: String? = null

    var otp: Int? = 0


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.resetFragment = this

        showBackButton(true)
        showTitle(true)
        setActionBar("Reset Password")



       binding.tvSubmit.setOnClickListener {
           if (
               binding.etNpassword.text.isBlank() ||
               binding.etCpassword.text.isBlank()
           ) {
               CommonCode.setToast(requireContext(), "Oops, we need more info.")
           } else if (!binding.etNpassword.text.toString()
                   .equals(binding.etCpassword.text.toString())
           ) {
               CommonCode.setToast(requireContext(), getString(R.string.password_mismatch))
           } else {

              openNextScreen()
           }


       }

    }




    fun openNextScreen() {

        this.findNavController().navigate(R.id.action_resetFragment_to_loginFragment)

    }
}