package com.mitv.proplayer.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mitv.proplayer.R
import com.mitv.proplayer.models.Channel

class ChannelAdapter(
    var channels: List<Channel>,
    private val onChannelClick: (Channel) -> Unit
) : RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder>() {

    // Variável para controlar o item com foco
    private var focusedItemPosition = -1

    class ChannelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val channelName: TextView = itemView.findViewById(R.id.channel_name)
        val channelLogo: ImageView = itemView.findViewById(R.id.channel_logo)
        val channelGroup: TextView = itemView.findViewById(R.id.channel_group)
        
        // Novo: número do canal
        val channelNumber: TextView = itemView.findViewById(R.id.channel_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_channel, parent, false)
        return ChannelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        val channel = channels[position]
        
        // Configurar os dados do canal
        holder.channelName.text = channel.name
        holder.channelGroup.text = channel.group ?: "Geral"
        holder.channelNumber.text = (position + 1).toString() // Número do canal
        
        // TODO: Carregar logo do canal depois
        // holder.channelLogo.setImageURI(channel.logo)
        
        // Configurar clique
        holder.itemView.setOnClickListener {
            onChannelClick(channel)
        }
        
        // Configurar foco para TV (IMPORTANTE para Android TV)
        holder.itemView.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                // Item com foco - animação de zoom
                view.animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).start()
                view.elevation = 16f
                focusedItemPosition = position
            } else {
                // Item sem foco - voltar ao normal
                view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200).start()
                view.elevation = 4f
            }
        }
        
        // Configurar foco inicial se for o primeiro item
        if (position == 0 && focusedItemPosition == -1) {
            holder.itemView.requestFocus()
        }
    }

    override fun getItemCount(): Int = channels.size
    
    // Método para atualizar a lista de canais
    fun updateChannels(newChannels: List<Channel>) {
        channels = newChannels
        notifyDataSetChanged()
    }
}
