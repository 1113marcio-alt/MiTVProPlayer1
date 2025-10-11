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
    private val channels: List<Channel>,
    private val onChannelClick: (Channel) -> Unit
) : RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder>() {

    class ChannelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val channelName: TextView = itemView.findViewById(R.id.channel_name)
        val channelLogo: ImageView = itemView.findViewById(R.id.channel_logo)
        val channelGroup: TextView = itemView.findViewById(R.id.channel_group)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_channel, parent, false)
        return ChannelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        val channel = channels[position]
        
        holder.channelName.text = channel.name
        holder.channelGroup.text = channel.group ?: "Geral"
        
        // TODO: Carregar logo do canal depois
        // holder.channelLogo.setImageURI(channel.logo)
        
        holder.itemView.setOnClickListener {
            onChannelClick(channel)
        }
        
        // Efeito de foco para TV (Android TV)
        holder.itemView.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).start()
                view.elevation = 8f
            } else {
                view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200).start()
                view.elevation = 2f
            }
        }
    }

    override fun getItemCount(): Int = channels.size
}
