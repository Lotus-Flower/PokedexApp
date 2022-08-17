package meehan.matthew.pokedexapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.mvrx.compose.collectAsState

@Composable
fun PokedexFavoritesScreen(
    viewModel: PokedexFavoritesViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.collectAsState()

    PokedexList(
        data = state.data,
        onItemChecked = { _, item ->
            viewModel.onFavoriteButtonChecked(item)
        },
        modifier = modifier
            .fillMaxSize()
    )
}
