package com.mitv.proplayer.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mitv.proplayer.R

class EPGFragment : Fragment(R.layout.fragment_epg) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Aqui vamos adicionar o EPG (Guia Eletrônico de Programação)
        // igual ao MyTVOnline 3 - Grade de programação
    }
}
