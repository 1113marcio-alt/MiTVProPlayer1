package com.mitv.proplayer.network

import com.mitv.proplayer.models.Channel

class M3UParser {
    
    fun parseM3UContent(m3uContent: String): List<Channel> {
        val channels = mutableListOf<Channel>()
        val lines = m3uContent.split("\n")
        
        var currentChannel: Channel? = null
        
        for (line in lines) {
            when {
                line.startsWith("#EXTINF:") -> {
                    currentChannel = parseExtinfLine(line)
                }
                line.startsWith("http") -> {
                    currentChannel?.let { channel ->
                        channel.url = line.trim()
                        channels.add(channel)
                    }
                    currentChannel = null
                }
            }
        }
        
        return channels
    }
    
    private fun parseExtinfLine(extinfLine: String): Channel {
        // Exemplo: #EXTINF:-1 tvg-id="CNN" tvg-name="CNN" group-title="News",CNN International
        val channel = Channel(
            id = "",
            name = "",
            url = "",
            logo = null,
            group = null
        )
        
        try {
            // Extrair nome do canal (depois da última vírgula)
            val nameStart = extinfLine.lastIndexOf(",") + 1
            if (nameStart > 0) {
                channel.name = extinfLine.substring(nameStart).trim()
            }
            
            // Extrair group-title
            val groupRegex = "group-title=\"([^\"]+)\"".toRegex()
            val groupMatch = groupRegex.find(extinfLine)
            channel.group = groupMatch?.groupValues?.get(1)
            
            // Extrair tvg-logo
            val logoRegex = "tvg-logo=\"([^\"]+)\"".toRegex()
            val logoMatch = logoRegex.find(extinfLine)
            channel.logo = logoMatch?.groupValues?.get(1)
            
            // Gerar ID único
            channel.id = "channel_${System.currentTimeMillis()}_${channels.size}"
            
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        return channel
    }
    
    companion object {
        private var channels = mutableListOf<Channel>()
    }
}
