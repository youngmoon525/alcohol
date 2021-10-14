package com.alcohol.finalalcohol.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alcohol.finalalcohol.R
import com.alcohol.finalalcohol.databinding.StartSecondFragmentBinding

class StartSecondFragment : Fragment() {

    private var _binding: StartSecondFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = StartSecondFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.txtPrevious.setOnClickListener {
            viewPager?.currentItem = 0
        }
        binding.txtNext.setOnClickListener {
            viewPager?.currentItem = 2
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = StartSecondFragment()
    }

}