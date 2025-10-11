package com.mitv.proplayer.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.mitv.proplayer.R
import com.mitv.proplayer.databinding.FragmentLiveTvBinding
import com.mitv.proplayer.models.Channel
import com.mitv.proplayer.ui.adapters.ChannelAdapter

class LiveTVFragment : Fragment(R.layout.fragment_live_tv) {

    private var _binding: FragmentLiveTvBinding? = null
    private val binding get() = _binding!!
    private lateinit var channelAdapter: ChannelAdapter

    // Lista de exemplo - depois vamos carregar M3U real
    private val sampleChannels = listOf(
        Channel("1", "CNN International", "http://example.com/cnn.m3u8", null, "News", false),
        Channel("2", "BBC World", "http://example.com/bbc.m3u8", null, "News", false),
        Channel("3", "Sport TV", "http://example.com/sport.m3u8", null, "Sports", false),
        Channel("4", "Movie Channel", "http://example.com/movie.m3u8", null, "Movies", false),
        Channel("5", "Music TV", "http://example.com/music.m3u8", null, "Music", false)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLiveTvBinding.bind(view)

        setupRecyclerView()
        loadChannels()
    }

    private fun setupRecyclerView() {
        channelAdapter = ChannelAdapter(sampleChannels) { channel ->
            // TODO: Abrir player quando clicar no canal
            println("Canal clicado: ${channel.name}")
        }

        binding.channelsRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3) // 3 colunas
            adapter = channelAdapter
            setHasFixedSize(true)
        }
    }

    private fun loadChannels() {
        // Por enquanto usa lista de exemplo
        channelAdapter.channels = sampleChannels
        channelAdapter.notifyDataSetChanged()
        
        // Mostrar quantos canais carregados
        binding.tvChannelCount.text = "Canais: ${sampleChannels.size}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
