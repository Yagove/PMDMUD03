package com.utad.pmdmud03

import android.content.Context

abstract class LenguageProvider {

   abstract val applicationContext: Context
    private lateinit var appDb: AppDatabase
    suspend fun cargarLista(context: Context): MutableList<Lenguage> {

        appDb = AppDatabase.getDatabase(context)
        return appDb.lenguageDao().getAll().toMutableList()


    }
}