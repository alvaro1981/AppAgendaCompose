package com.example.agendaapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.agendaapp.models.Cita

@Database(
    entities = [Cita::class],
    version = 1,
    exportSchema = false
)
abstract class AgendaDatabase:RoomDatabase() {
   abstract fun citasDao():AgendaDatabaseDao
}