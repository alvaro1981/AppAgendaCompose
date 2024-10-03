package com.example.agendaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.agendaapp.navigation.NavManager
import com.example.agendaapp.room.AgendaDatabase
import com.example.agendaapp.ui.theme.AgendaAppTheme
import com.example.agendaapp.viewmodels.AgendaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgendaAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val database = Room.databaseBuilder(
                        this,
                         AgendaDatabase::class.java,
                        "db_agenda"
                    ).build()

                    val dao = database.citasDao()
                    val viewModel = AgendaViewModel(dao)
                    NavManager(viewModel = viewModel)

                }
            }
        }
    }
}

