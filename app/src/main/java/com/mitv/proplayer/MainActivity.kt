package com.mitv.proplayer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mitv.proplayer.R

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Log para debug
        println("✅ MiTV Pro Player - App iniciado!")
    }
}
