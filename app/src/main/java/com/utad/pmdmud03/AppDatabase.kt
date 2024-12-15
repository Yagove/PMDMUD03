package com.utad.pmdmud03

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Lenguage::class, Project::class], version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun lenguageDao():LenguageDAO
    abstract fun projectDao():ProjectDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase (context: Context):AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){return tempInstance}
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}