package com.utad.pmdmud03

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.utad.pmdmud03.databinding.ActivityLenguagesBinding

class LenguagesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLenguagesBinding // ViewBinding instance
    private lateinit var appDb: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityLenguagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()


        appDb = AppDatabase.getDatabase(this)
    }
}

