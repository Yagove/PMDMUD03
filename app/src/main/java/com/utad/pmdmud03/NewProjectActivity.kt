package com.utad.pmdmud03

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.utad.pmdmud03.databinding.ActivityNewProjectBinding
import kotlinx.coroutines.launch

class NewProjectActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityNewProjectBinding
    private val binding: ActivityNewProjectBinding get() = _binding

    private lateinit var appDb: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Yago

        _binding = ActivityNewProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDb = AppDatabase.getDatabase(this)

        // Configurar el spinner con lenguajes
        val lenguages = listOf(
            "Java", "Kotlin", "Python", "JavaScript",
            "C++", "C#", "PHP", "Ruby", "Swift"
        )

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, lenguages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spLeng.adapter = adapter

        // Listener para guardar el proyecto
        binding.btnAddProject.setOnClickListener {
            guardarProyecto()
        }
    }

    private fun guardarProyecto() {
        // Validar los campos del formulario
        val name = binding.etProjectName.text.toString()
        val description = binding.etProjectDescription.text.toString()
        val hours = binding.etHours.text.toString().toIntOrNull() ?: 0
        val detail = binding.etDetail.text.toString()
        val priority = when (binding.rgPriority.checkedRadioButtonId) {
            binding.rbLow.id -> 1
            binding.rbMedium.id -> 2
            binding.rbHigh.id -> 3
            else -> 0
        }
        val lenguage = binding.spLeng.selectedItem.toString()

        if (name.isBlank() || description.isBlank() || priority == 0) {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            return
        }



        // Insertar en la base de datos
        lifecycleScope.launch {
            appDb.projectDao().insert(Project(name, description, detail, hours, priority,lenguage))
            Toast.makeText(this@NewProjectActivity, "Proyecto guardado.", Toast.LENGTH_SHORT).show()

            // Notificar a ProjectsActivity que hay nuevos datos
            setResult(RESULT_OK)
            finish() // Cerrar la actividad
        }
    }
}

