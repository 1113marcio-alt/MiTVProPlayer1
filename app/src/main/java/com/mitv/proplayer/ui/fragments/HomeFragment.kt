package com.mitv.proplayer.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mitv.proplayer.R
import com.mitv.proplayer.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        
        setupUI()
    }

    private fun setupUI() {
        // Setup da tela inicial estilo MyTVOnline 3
        setupNavigation()
    }

    private fun setupNavigation() {
        // Navegação entre seções (será implementado)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
