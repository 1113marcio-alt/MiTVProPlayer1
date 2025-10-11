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
        var channelName = "Canal"
        var groupTitle: String? = null
        var logoUrl: String? = null
        
        try {
            val nameStart = extinfLine.lastIndexOf(",") + 1
            if (nameStart > 0) {
                channelName = extinfLine.substring(nameStart).trim()
            }
            
            val groupRegex = "group-title=\"([^\"]+)\"".toRegex()
            val groupMatch = groupRegex.find(extinfLine)
            groupTitle = groupMatch?.groupValues?.get(1)
            
            val logoRegex = "tvg-logo=\"([^\"]+)\"".toRegex()
            val logoMatch = logoRegex.find(extinfLine)
            logoUrl = logoMatch?.groupValues?.get(1)
            
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        return Channel(
            id = System.currentTimeMillis().toString(),
            name = channelName,
            url = "",
            logo = logoUrl,
            group = groupTitle
        )
    }
}
