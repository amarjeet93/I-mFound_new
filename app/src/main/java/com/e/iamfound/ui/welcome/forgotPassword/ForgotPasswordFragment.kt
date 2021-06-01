package com.e.iamfound.ui.welcome.forgotPassword

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.e.iamfound.R
import com.e.iamfound.base.BaseFragment
import com.e.iamfound.utils.CommonCode
import com.e.iamfound.databinding.ForgotPasswordFragmentBinding


class ForgotPasswordFragment : BaseFragment<ForgotPasswordFragmentBinding>(), View.OnClickListener {

    override fun getLayoutRes() = R.layout.forgot_password_fragment
    var otp: Int? = 1234

    var final_otp = 0


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.forgotFragment = this
        showActionBar(true)



        showBackButton(true)
        showTitle(true)
        setActionBar("Forgot Password")

        binding.tvSubmit.setOnClickListener(this)

        binding.tvSendOtp.setOnClickListener(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOTPBoxAutoNavigation()
    }


    fun setOTPBoxAutoNavigation() {
        setTextChangeListener(binding.otp1, binding.otp2)
        setTextChangeListener(binding.otp2, binding.otp3)
        setTextChangeListener(binding.otp3, binding.otp4)
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


    fun gotToNext() {
        if (binding.otp1.text.toString() != "" || binding.otp2.text.toString() != ""
            || binding.otp3.text.toString() != "" || binding.otp4.text.toString() != ""
        ) {

            var enteredOtpNumber =
                "${binding.otp1.text}${binding.otp2.text}${binding.otp3.text}${binding.otp4.text}"

            var enteredOtpInInt = enteredOtpNumber.toInt()

            if (enteredOtpInInt == otp) {
                final_otp = enteredOtpInInt


                this.findNavController().navigate(R.id.action_forgotFragment_to_resetFragment)
            } else {

                CommonCode.setToast(requireContext(), getString(R.string.otpMismatch))
            }
        }else {
            CommonCode.setToast(requireContext(), getString(R.string.otpMismatch))
        }
    }


    fun resend() {

        if (binding.etPhone.text.toString() == "") {
            CommonCode.setToast(requireContext(), getString(R.string.enter_phone))

        } else {
            binding.otp1.setText("")
            binding.otp2.setText("")
            binding.otp3.setText("")
            binding.otp4.setText("")
            gotToNext()
        }

    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_submit -> {
                gotToNext()
            }

            R.id.tv_send_otp -> {
                resend()
            }


        }
    }


}