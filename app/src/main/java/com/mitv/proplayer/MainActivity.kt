package com.mitv.proplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mitv.proplayer.databinding.ActivityMainBinding
import com.mitv.proplayer.ui.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupHomeFragment()
    }
    
    private fun setupHomeFragment() {
        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}
