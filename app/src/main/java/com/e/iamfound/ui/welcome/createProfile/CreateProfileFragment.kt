package com.e.iamfound.ui.welcome.createProfile

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.e.iamfound.R
import com.e.iamfound.base.BaseFragment
import com.e.iamfound.utils.CommonCode
import com.e.iamfound.databinding.FragmentCreateProfileBinding
import com.e.iamfound.ui.home.main.HomeActivity
import com.e.iamfound.ui.welcome.splash.InitialActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import java.io.File


class CreateProfileFragment : BaseFragment<FragmentCreateProfileBinding>(),View.OnClickListener {
    override fun getLayoutRes() = R.layout.fragment_create_profile
    private var userfile: File? = null
    val PERMISSION_ID = 42
    val REQUEST_CAMERA = 0
    val SELECT_PHOTO = 1002

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.createProfileFragment = this

        showBackButton(false)
        showTitle(false)


        binding.tvUpload.setOnClickListener(this)
        binding.tvRegister.setOnClickListener {
            if (binding.etName.text.isBlank() ||
                binding.etEmail.text.isBlank() ||
                binding.etAddress.text.isBlank() ||
                binding.etAphone.text.isBlank() ||
                binding.etReward.text.isBlank()
            ) {
                CommonCode.setToast(requireContext(), "Oops, we need more info.")
            } else {
                openNextScreen()
            }
        }

    }




    private fun checkPermissions(): Boolean {
        if (activity is InitialActivity) {
            if (ActivityCompat.checkSelfPermission(
                    activity as InitialActivity,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    activity as InitialActivity,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED&&
                ActivityCompat.checkSelfPermission(
                    activity as InitialActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            )
                return true
        } else {
            if (ActivityCompat.checkSelfPermission(
                    activity as HomeActivity,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    activity as HomeActivity,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            )
                return true
        }
        return false
    }


    private fun requestPermissions() {
        showProgressBar(false)
        if (activity is HomeActivity) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                PERMISSION_ID
            )
        } else {
            requestPermissions(
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                PERMISSION_ID
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {

            PERMISSION_ID -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    selectImage()
                } else {
                    CommonCode.setToast(requireContext(), "Permission denied")
                }
            }
        }
    }



    fun openNextScreen() {
        startActivity(Intent(activity, HomeActivity::class.java))
        activity?.finish()

    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.tv_upload->
            {
                if(checkPermissions())
                {
                    selectImage()
                }else{
                    requestPermissions()
                }

            }
        }
    }

    var imageUri: Uri? = null
    private fun selectImage() {
        ImagePicker.with(this)
            .crop()
            .start(SELECT_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_PHOTO) {
            imageUri = data!!.data
            binding.imageView.setImageURI(imageUri)
            userfile = ImagePicker.getFile(data)!!

        } else if (requestCode == REQUEST_CAMERA) {

            imageUri = data!!.data
            binding.imageView.setImageURI(imageUri)
            userfile = ImagePicker.getFile(data)!!
        }
    }



}