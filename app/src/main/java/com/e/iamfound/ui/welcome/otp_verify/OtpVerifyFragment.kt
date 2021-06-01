package com.e.iamfound.ui.welcome.otp_verify

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.e.iamfound.R
import com.e.iamfound.base.BaseFragment
import com.e.iamfound.utils.*
import com.e.iamfound.databinding.FragmentOtpVerifyBinding

class OtpVerifyFragment : BaseFragment<FragmentOtpVerifyBinding>() {
    override fun getLayoutRes() = R.layout.fragment_otp_verify

    var resend = ""

    var mobileNumber: String? = null

    var password: String? = null

    var final_otp: Int? = null

    var final_emailOtp: Int? = null

    var otp: Int? = 1234

    var Emailotp: Int? = 1234


    var deviceToken = "12345678"

    var emailId = ""


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.otpFragment = this


        showBackButton(false)
        showTitle(false)

        val data: Bundle? = arguments


        editiorListener()
        binding.tvSubmit.setOnClickListener {
            gotToHome()
        }

    }

    fun editiorListener() {
        setTextChangeListener(binding.etOtp1, binding.etOtp2)

        setTextChangeListener(binding.etOtp2, binding.etOtp3)

        setTextChangeListener(binding.etOtp3, binding.etOtp4)

//        for email
        setTextChangeListener(binding.etOtp1Email, binding.etOtp2Email)

        setTextChangeListener(binding.etOtp2Email, binding.etOtp3Email)

        setTextChangeListener(binding.etOtp3Email, binding.etOtp4Email)
    }

    fun setTextChangeListener(fromEditText: EditText, toEditText: EditText) {

        fromEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.toString().isNotEmpty()) {

                    focusChange(toEditText)
                }
            }
        })
    }

    fun focusChange(editText: EditText) {

        editText.requestFocus()

        val imm: InputMethodManager? =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?

        imm?.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    fun validateOtp(): Boolean {

        if (binding.etOtp1.text.toString() == "" || binding.etOtp2.text.toString() == ""
            || binding.etOtp3.text.toString() == "" || binding.etOtp4.text.toString() == "" ||
            binding.etOtp1Email.text.toString() == "" || binding.etOtp2Email.text.toString() == "" ||
            binding.etOtp3Email.text.toString() == "" || binding.etOtp4Email.text.toString() == ""
        ) {

            CommonCode.setToast(requireContext(), getString(R.string.mobileOtpNotFound))
            return false
        }
        return true
    }

    fun gotToHome() {
        if (binding.etOtp1.text.toString() != "" || binding.etOtp2.text.toString() != ""
            || binding.etOtp3.text.toString() != "" || binding.etOtp4.text.toString() != "" ||
            binding.etOtp1Email.text.toString() != "" || binding.etOtp2Email.text.toString() != "" ||
            binding.etOtp3Email.text.toString() != "" || binding.etOtp4Email.text.toString() != ""
        ) {

            var enteredOtpNumber =
                "${binding.etOtp1.text}${binding.etOtp2.text}${binding.etOtp3.text}${binding.etOtp4.text}"

            //email otp
            var enteredEmailOtp =
                "${binding.etOtp1Email.text}${binding.etOtp2Email.text}${binding.etOtp3Email.text}${binding.etOtp4Email.text}"

            var enteredOtpInInt = enteredOtpNumber.toInt()

if( enteredEmailOtp.equals(""))
{
    var enteredEmailOtpInInt=0
    CommonCode.setToast(requireContext(), "Enter OTP")

}else {
    //email otp int
    var enteredEmailOtpInInt = enteredEmailOtp.toInt() ?: 0

            if (enteredOtpInInt == otp && enteredEmailOtpInInt == Emailotp) {
                final_otp = enteredOtpInInt
                final_emailOtp = enteredEmailOtpInInt
                openNextScreen()
            } else {

                CommonCode.setToast(requireContext(), getString(R.string.otpMismatch))
            }
}
        } else {
            validateOtp()
        }
    }


    fun openNextScreen() {


        this.findNavController().navigate(R.id.action_otpFragment_to_createProfileFragment)

    }


}