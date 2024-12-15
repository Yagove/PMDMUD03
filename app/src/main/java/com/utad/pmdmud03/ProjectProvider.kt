package com.utad.pmdmud03

import android.content.Context

class ProjectProvider {


    private lateinit var appDb: AppDatabase

    suspend fun cargarLista(context: Context): MutableList<Project> {

        appDb = AppDatabase.getDatabase(context)
        return appDb.projectDao().getAll().toMutableList()

    }
}