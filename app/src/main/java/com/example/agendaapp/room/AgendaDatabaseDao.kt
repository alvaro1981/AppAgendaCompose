package com.example.agendaapp.room

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.agendaapp.models.Cita
import kotlinx.coroutines.flow.Flow

interface AgendaDatabaseDao {
    @Query("SELECT * FROM citas")
    fun obtenerCitas(): Flow<List<Cita>>

    @Insert
    suspend fun agregarCita(cita:Cita)

    @Update
    suspend fun actualizarCita(cita:Cita)

    @Delete
    suspend fun borrarCita(cita:Cita)
}