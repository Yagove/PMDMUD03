package com.utad.pmdmud03

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.utad.pmdmud03.databinding.ActivityNewLenguageBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewLenguageActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityNewLenguageBinding
    private val binding: ActivityNewLenguageBinding get() = _binding

    private lateinit var appDb: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityNewLenguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDb = AppDatabase.getDatabase(this)

        binding.btnAddLeng.setOnClickListener {
            guardarLenguaje()
            finish()
        }


    }

    private fun guardarLenguaje() {

        val name = binding.etNewLeng.text.toString()


        if (name.isNotEmpty()) {

            val lenguage = Lenguage(name, R.drawable.ic_launcher_foreground)

            lifecycleScope.launch(Dispatchers.IO) {
                appDb.lenguageDao().insert(lenguage)
            }
        }
    }
}
