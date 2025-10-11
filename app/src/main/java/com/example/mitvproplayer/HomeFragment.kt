package com.example.mitvproplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Configurar clique do botão Live TV
        view.findViewById<View>(R.id.btnLiveTV)?.setOnClickListener {
            navigateToLiveTV()
        }
        
        // Configurar clique do botão Settings (se existir)
        view.findViewById<View>(R.id.btnSettings)?.setOnClickListener {
            // TODO: Navegar para configurações
        }
    }

    private fun navigateToLiveTV() {
        findNavController().navigate(R.id.action_home_to_liveTV)
    }
}
