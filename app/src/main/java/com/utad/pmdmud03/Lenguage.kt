package com.utad.pmdmud03

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tabla_lenguages")
data class Lenguage (var name: String, var icon: Int){


    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    constructor(name: String, icon: Int, id: Int) : this(name, icon) {
        this.id = id

    }

}