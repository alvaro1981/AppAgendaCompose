package com.example.agendaapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "citas")
data class Cita (
    @PrimaryKey(autoGenerate = false)
    val idCita:String,
    @ColumnInfo("nomPaciente")
    var nomPaciente:String,
    @ColumnInfo("telPaciente")
    var telPaciente:String,
    @ColumnInfo("asunto")
    var asunto: String,
    @ColumnInfo("diaCita")
    val diaCita:String,
    @ColumnInfo("horaCita")
    val horaCita:String
)