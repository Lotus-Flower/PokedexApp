package meehan.matthew.pokedexapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.mvrx.compose.collectAsState

@Composable
fun PokedexListScreen(
    viewModel: PokedexListViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.collectAsState()

    PokedexList(
        data = state.data,
        modifier = modifier
    )
}
