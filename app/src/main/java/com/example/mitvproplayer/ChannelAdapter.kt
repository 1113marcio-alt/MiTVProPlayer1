package com.example.mitvproplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChannelAdapter(
    private val channels: List<Channel>,
    private val onItemClick: (Channel) -> Unit
) : RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder>() {

    // Data class para representar um canal
    data class Channel(
        val id: Int,
        val number: String,
        val name: String,
        val logoUrl: String? = null,
        val streamUrl: String
    )

    class ChannelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val channelNumber: TextView = itemView.findViewById(R.id.channelNumber)
        val channelLogo: ImageView = itemView.findViewById(R.id.channelLogo)
        val channelName: TextView = itemView.findViewById(R.id.channelName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_channel, parent, false)
        return ChannelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        val channel = channels[position]
        
        holder.channelNumber.text = channel.number
        holder.channelName.text = channel.name
        
        // TODO: Carregar logo do canal (usar Glide/Picasso depois)
        // Por enquanto, placeholder
        holder.channelLogo.setImageResource(R.drawable.ic_tv_channel_placeholder)
        
        // Click listener
        holder.itemView.setOnClickListener {
            onItemClick(channel)
        }
        
        // Foco para TV (Android TV)
        holder.itemView.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).start()
            } else {
                view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200).start()
            }
        }
    }

    override fun getItemCount(): Int = channels.size
}
