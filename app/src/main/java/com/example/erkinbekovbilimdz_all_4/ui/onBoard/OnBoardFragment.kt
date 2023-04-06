package com.example.erkinbekovbilimdz_all_4.ui.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.erkinbekovbilimdz_all_4.data.local.Pref
import com.example.erkinbekovbilimdz_all_4.databinding.FragmentOnBoardBinding
import com.example.erkinbekovbilimdz_all_4.ui.onBoard.adapter.OnBoardingAdapter

class OnBoardFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardBinding
    private val adapter = OnBoardingAdapter(this::onStartClick, this::onNextClick)
    private lateinit var pref : Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        binding.viewPager.adapter = adapter
        binding.indicatorDots.attachTo(binding.viewPager)
    }
    private fun onStartClick(){
        pref.saveSeen()
        findNavController().navigateUp()
    }

    private fun onNextClick(){
        binding.viewPager.currentItem++
    }

}