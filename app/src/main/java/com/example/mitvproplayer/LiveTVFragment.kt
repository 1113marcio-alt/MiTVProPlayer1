package com.example.mitvproplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LiveTVFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var emptyStateText: TextView
    private lateinit var channelAdapter: ChannelAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_live_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViews(view)
        setupRecyclerView()
        loadChannels()
    }

    private fun setupViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerViewChannels)
        progressBar = view.findViewById(R.id.progressBar)
        emptyStateText = view.findViewById(R.id.emptyStateText)
    }

    private fun setupRecyclerView() {
        // Layout em grid para melhor visualização na TV
        val layoutManager = GridLayoutManager(requireContext(), 4) // 4 colunas
        recyclerView.layoutManager = layoutManager
        
        // Melhora performance para listas fixas
        recyclerView.setHasFixedSize(true)
        
        // Inicializa adapter com lista vazia
        channelAdapter = ChannelAdapter(emptyList()) { channel ->
            onChannelClick(channel)
        }
        recyclerView.adapter = channelAdapter
    }

    private fun loadChannels() {
        showLoading(true)
        
        // Simula carregamento de dados
        // TODO: Substituir por dados reais do ViewModel
        val mockChannels = listOf(
            ChannelAdapter.Channel(1, "101", "Globo", "", "http://stream.globo.com"),
            ChannelAdapter.Channel(2, "102", "SBT", "", "http://stream.sbt.com"),
            ChannelAdapter.Channel(3, "103", "Record", "", "http://stream.record.com"),
            ChannelAdapter.Channel(4, "104", "Band", "", "http://stream.band.com"),
            ChannelAdapter.Channel(5, "105", "RedeTV", "", "http://stream.redetv.com"),
            ChannelAdapter.Channel(6, "201", "HBO", "", "http://stream.hbo.com"),
            ChannelAdapter.Channel(7, "202", "Fox", "", "http://stream.fox.com"),
            ChannelAdapter.Channel(8, "203", "Discovery", "", "http://stream.discovery.com")
        )
        
        // Simula delay de rede
        recyclerView.postDelayed({
            channelAdapter = ChannelAdapter(mockChannels) { channel ->
                onChannelClick(channel)
            }
            recyclerView.adapter = channelAdapter
            showLoading(false)
            
            if (mockChannels.isEmpty()) {
                showEmptyState(true)
            }
        }, 1000)
    }

    private fun onChannelClick(channel: ChannelAdapter.Channel) {
        // TODO: Implementar navegação para player de vídeo
        // Por enquanto, apenas log
        println("Canal clicado: ${channel.name} - ${channel.streamUrl}")
        
        // Mostrar mensagem simples
        // Toast.makeText(requireContext(), "Abrindo: ${channel.name}", Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
        recyclerView.visibility = if (show) View.GONE else View.VISIBLE
    }

    private fun showEmptyState(show: Boolean) {
        emptyStateText.visibility = if (show) View.VISIBLE else View.GONE
        recyclerView.visibility = if (show) View.GONE else View.VISIBLE
    }
}
