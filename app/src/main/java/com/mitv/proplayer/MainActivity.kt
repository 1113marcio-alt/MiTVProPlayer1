package com.mitv.proplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mitv.proplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // App inicializado com sucesso!
        setupUI()
    }
    
    private fun setupUI() {
        // Configurações iniciais da interface
        // Será implementado nos próximos passos
    }
}
