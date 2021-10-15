package com.alcohol.finalalcohol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alcohol.finalalcohol.databinding.StartOnBoardingFragmentBinding
import com.alcohol.finalalcohol.onboarding.StartFirstFragment
import com.alcohol.finalalcohol.onboarding.StartSecondFragment
import com.alcohol.finalalcohol.onboarding.StartThirdFragment

class StartOnBoardingFragment : Fragment() {

    private var _binding: StartOnBoardingFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = StartOnBoardingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //1
        setupViewPager()
    }

    private fun setupViewPager() {
        val fragmentList = arrayListOf(
            StartFirstFragment.newInstance(),
            StartSecondFragment.newInstance(),
            StartThirdFragment.newInstance()
        )

        val adapter = StartViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter
        //2
        binding.viewPager.isUserInputEnabled = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}