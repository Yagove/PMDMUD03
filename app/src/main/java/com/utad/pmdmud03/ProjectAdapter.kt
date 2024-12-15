package com.utad.pmdmud03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utad.pmdmud03.databinding.ItemProjectBinding

class ProjectAdapter(
    var projects: List<Project>,
    private val onDeleteProject: (Project) -> Unit // Callback para eliminar proyecto
) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    class ProjectViewHolder(val binding: ItemProjectBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        // Inflar el binding
        val binding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = projects[position]
        // Usar binding para asignar los datos
        holder.binding.tvProjectName.text = project.name
        holder.binding.tvProjectDescription.text = project.description
        holder.binding.tvHours.text = project.hours.toString()
        holder.binding.tvPriority.text = project.priority.toString()
        holder.binding.tvProjectLeng.text = project.projectLenguage

        // Configurar el botón para borrar el proyecto
        holder.binding.btnEraseProject.setOnClickListener {
            onDeleteProject(project) // Llamar al callback con el proyecto a eliminar
        }
    }

    override fun getItemCount(): Int {
        return projects.size
    }
}

