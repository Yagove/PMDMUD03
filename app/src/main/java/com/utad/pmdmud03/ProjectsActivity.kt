package com.utad.pmdmud03

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.utad.pmdmud03.databinding.ActivityProjectsBinding
import kotlinx.coroutines.launch

class ProjectsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProjectsBinding
    private lateinit var appDb: AppDatabase
    private lateinit var adapter: ProjectAdapter // Adaptador para el RecyclerView
    private var projects: List<Project> = emptyList() // Lista de proyectos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProjectsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        appDb = AppDatabase.getDatabase(this)



        // Configurar el RecyclerView
        setupRecyclerView()

        // Cargar los proyectos al iniciar la actividad
        loadProjects()

        binding.btnNewLeng.setOnClickListener {
            irANewLenguageActivity()
        }

        binding.btnAddProject.setOnClickListener {
            irANewProjectActivity()
        }
    }

    private fun setupRecyclerView() {
        adapter = ProjectAdapter(projects) { projectToDelete ->
            deleteProject(projectToDelete) // Manejar el borrado
        }
        binding.rvProjects.layoutManager = LinearLayoutManager(this)
        binding.rvProjects.adapter = adapter
    }


    private fun loadProjects() {
        lifecycleScope.launch {
            projects = appDb.projectDao().getAll()
            adapter.projects = projects // Actualizar la lista en el adaptador
            adapter.notifyDataSetChanged()
        }
    }

    private fun irANewProjectActivity() {
        val intent = Intent(this, NewProjectActivity::class.java)
        startActivityForResult(intent, NEW_PROJECT_REQUEST_CODE) // Esperar resultado
    }

    private fun irANewLenguageActivity() {
        val intent = Intent(this, NewLenguageActivity::class.java)
        startActivity(intent)
    }

    // Manejar el resultado de NewProjectActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_PROJECT_REQUEST_CODE && resultCode == RESULT_OK) {
            // Recargar la lista de proyectos
            loadProjects()
        }
    }
    private fun deleteProject(project: Project) {
        lifecycleScope.launch {
            appDb.projectDao().delete(project) // Eliminar el proyecto de la base de datos
            projects = projects.filter { it.id != project.id } // Actualizar la lista local
            adapter.projects = projects // Actualizar el adaptador
            adapter.notifyDataSetChanged()
            Toast.makeText(this@ProjectsActivity, "Proyecto eliminado", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val NEW_PROJECT_REQUEST_CODE = 1
    }
}


