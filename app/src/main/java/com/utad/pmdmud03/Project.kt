package com.utad.pmdmud03

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tabla_projects")
data class Project(
    var projectID: Int,
    var name: String,
    var description: String,
    var date: String,
    var hours: Int,
    var priority: Int,
    var projectLenguage: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    // Constructor secundario corregido
    constructor(
        name: String,
        description: String,
        date: String,
        hours: Int,
        priority: Int,
        projectLenguage: String
    ) : this(0, name, description, date, hours, priority, projectLenguage)
}
