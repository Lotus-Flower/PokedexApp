package meehan.matthew.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.airbnb.mvrx.compose.mavericksActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import meehan.matthew.pokedexapp.ui.theme.PokedexAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: PokedexAppViewModel = mavericksActivityViewModel()

            PokedexAppTheme {
                PokedexAppScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}
