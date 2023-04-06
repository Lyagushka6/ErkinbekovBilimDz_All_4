package com.example.erkinbekovbilimdz_all_4.ui.Profile


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.example.erkinbekovbilimdz_all_4.data.local.Pref
import com.example.erkinbekovbilimdz_all_4.databinding.FragmentProfileBinding
import com.example.erkinbekovbilimdz_all_4.utils.glideLoadImage

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                val uri: Uri? = it.data?.data
                pref.saveImage(uri.toString())
                binding.imgProfile.glideLoadImage(uri.toString())
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        binding.imgProfile.glideLoadImage(pref.getImage())
        binding.imgProfile.setOnClickListener {
            openGallery()
        }
        saveName()
    }


    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_PICK
        launcher.launch(intent)
    }

    private fun saveName() {
        binding.etName.setText(pref.getNameText())
        binding.etName.addTextChangedListener {
            pref.saveNameText(binding.etName.text.toString())
        }
    }
}
