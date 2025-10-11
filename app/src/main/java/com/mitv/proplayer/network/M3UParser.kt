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
                        val channelWithUrl = channel.copy(url = line.trim())
                        channels.add(channelWithUrl)
                    }
                    currentChannel = null
                }
            }
        }
        
        return channels
    }
    
    private fun parseExtinfLine(extinfLine: String): Channel {
        // Exemplo: #EXTINF:-1 tvg-id="CNN" tvg-name="CNN" group-title="News",CNN International
        var channelName = "Canal"
        var groupTitle: String? = null
        var logoUrl: String? = null
        
        try {
            // Extrair nome do canal (depois da última vírgula)
            val nameStart = extinfLine.lastIndexOf(",") + 1
            if (nameStart > 0) {
                channelName = extinfLine.substring(nameStart).trim()
            }
            
            // Extrair group-title
            val groupRegex = "group-title=\"([^\"]+)\"".toRegex()
            val groupMatch = groupRegex.find(extinfLine)
            groupTitle = groupMatch?.groupValues?.get(1)
            
            // Extrair tvg-logo
            val logoRegex = "tvg-logo=\"([^\"]+)\"".toRegex()
            val logoMatch = logoRegex.find(extinfLine)
            logoUrl = logoMatch?.groupValues?.get(1)
            
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        return Channel(
            id = System.currentTimeMillis().toString(),
            name = channelName,
            url = "", // URL será adicionada depois
            logo = logoUrl,
            group = groupTitle
        )
    }
}
