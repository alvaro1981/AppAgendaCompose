package com.example.agendaapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agendaapp.models.Cita
import com.example.agendaapp.room.AgendaDatabaseDao
import com.example.agendaapp.states.AgendaState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AgendaViewModel (
    private val dao:AgendaDatabaseDao
):ViewModel() {

   var state by mutableStateOf(AgendaState())
        private set
   init {
       viewModelScope.launch {
           dao.obtenerCitas().collectLatest {
               state = state.copy(listaCitas = it)
           }
       }
   }

    fun agregarCita(cita: Cita)= viewModelScope.launch {
        dao.agregarCita(cita = cita)
    }
    fun actualizarCita(cita: Cita)= viewModelScope.launch {
        dao.actualizarCita(cita = cita)
    }
    fun borrarCita(cita: Cita)= viewModelScope.launch {
        dao.borrarCita(cita = cita)
    }

}
