package com.example.agendaapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agendaapp.models.Cita
import com.example.agendaapp.viewmodels.AgendaViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarCitaView(
    navController: NavController,
    viewModel: AgendaViewModel
){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Agregar Cita",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "regresar",
                            tint = Color.White
                        )
                    }
                }

            )
        },

    ){
        AgregarCitaContentView(
            it = it,
            navController = navController,
            viewModel = viewModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarCitaContentView(
    it:PaddingValues,
    navController: NavController,
    viewModel: AgendaViewModel
){
    val keyboardController = LocalSoftwareKeyboardController.current

    var nomPaciente by remember { mutableStateOf("") }
    var telPaciente by remember { mutableStateOf("") }
    var asunto by remember { mutableStateOf("") }

    val diasLista = listOf("Seleccionar un dia" , "Lunes","Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo")
    var showDias by remember { mutableStateOf(false) }
    var diaSeleccionado by remember { mutableStateOf(diasLista[0]) }

    val horasLista = listOf(
        "SElecciona una hora",
        "9:00 a 10:00",
        "10:00 a 11:00",
        "11:00 a 12:00",
        "12:00 a 13:00",
        "13:00 a 14:00",
        "14:00 a 15:00",
        "15:00 a 16:00",
        "16:00 a 17:00",
        "17:00 a 18:00",
        "18:00 a 19:00",
        "19:00 a 20:00"
    )
    var showHoras by remember { mutableStateOf(false) }
    var horaSeleccionado by remember { mutableStateOf(horasLista[0]) }

    val maxTel = 10

    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
     OutlinedTextField(
         modifier = Modifier
             .fillMaxWidth()
             .padding(horizontal = 30.dp)
             .padding(bottom = 15.dp),
         value = nomPaciente,
         onValueChange = {
             nomPaciente = it
         },
         label = {
             Text(text = "Nombre del paciente")
         },
         keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
     )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp),
            value = telPaciente,
            onValueChange = {
                telPaciente = it
            },
            label = {
                Text(text = "telefono del paciente")
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp),
            value = asunto,
            onValueChange = {
                asunto = it
            },
            label = {
                Text(text = "asunto")
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
        )

        ExposedDropdownMenuBox(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp),
            expanded = showDias,
            onExpandedChange = {
                showDias =! showDias
            }
        ) {
           keyboardController?.hide()

            TextField(value = diaSeleccionado,
                onValueChange ={ },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = showDias)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )

            ExposedDropdownMenu(expanded = showDias,
                onDismissRequest = {
                    showDias= false
                }
            ) {
                diasLista.forEachIndexed{ index, s ->
                    DropdownMenuItem(
                        text = {
                             Text(text = s)
                        },
                        onClick = {
                            if(s != diasLista[0]) {
                                diaSeleccionado = s
                            }
                            showDias = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }

        ExposedDropdownMenuBox(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp),
            expanded = showHoras,
            onExpandedChange = {
                showHoras =! showHoras
            }
        ) {
            keyboardController?.hide()

            TextField(value = horaSeleccionado,
                onValueChange ={ },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = showHoras)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )

            ExposedDropdownMenu(expanded = showHoras,
                onDismissRequest = {
                    showHoras= false
                }
            ) {
                horasLista.forEachIndexed{ index, s ->
                    DropdownMenuItem(
                        text = {
                            Text(text = s)
                        },
                        onClick = {
                            if(s != horasLista[0]) {
                                horaSeleccionado = s
                            }
                            showHoras = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp),
            onClick = {
                val cita = Cita(
                    System.currentTimeMillis().toString(),
                    nomPaciente,
                    telPaciente,
                    asunto,
                    diaSeleccionado,
                    horaSeleccionado
                )
                viewModel.agregarCita(cita = cita)
                navController.popBackStack()
            }
        ) {
            Text(text = "Agendar Cita")


        }

    }
}