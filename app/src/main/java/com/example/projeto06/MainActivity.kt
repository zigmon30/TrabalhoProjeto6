package com.example.projeto06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.projeto06.ui.theme.Projeto06Theme
import com.example.projeto06.views.PersonagemFactory
import com.example.projeto06.views.PersonagensListScreen
import com.example.projeto06.views.PersonagensViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<PersonagensViewModel>(){
            PersonagemFactory(
                (this.applicationContext as NarrutoPersonagensAplication).repository
            )
        }
        setContent {
            Projeto06Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HarryPotterPersonagens(viewModel)
                }
            }
        }
    }
}

@Composable
fun HarryPotterPersonagens(
    viewModel: PersonagensViewModel
) {
    PersonagensListScreen(personagensViewModel = viewModel)
}
