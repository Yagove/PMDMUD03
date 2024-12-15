package com.utad.pmdmud03

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.utad.pmdmud03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding: ActivityMainBinding get() = _binding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        binding.btnAccess.setOnClickListener {
            irAProjectsActiviy()
        }

        binding.btnCreate.setOnClickListener {
            irARegistroActivity()
        }

    }

    private fun irARegistroActivity() {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }

    private fun irAProjectsActiviy() {
        val intent = Intent(this, ProjectsActivity::class.java)
        startActivity(intent)
    }
}


