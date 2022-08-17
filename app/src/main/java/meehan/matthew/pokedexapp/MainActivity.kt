package meehan.matthew.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import meehan.matthew.pokedexapp.ui.theme.PokedexAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: PokedexAppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexAppTheme {
                PokedexAppScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}
