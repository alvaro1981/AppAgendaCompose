package com.example.agendaapp.states

import com.example.agendaapp.models.Cita

data class AgendaState(
    var listaCitas:List<Cita>   = emptyList()
)
