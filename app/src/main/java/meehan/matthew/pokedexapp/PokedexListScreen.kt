package meehan.matthew.pokedexapp

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.mvrx.compose.collectAsState

@Composable
fun PokedexListScreen(
    viewModel: PokedexListViewModel
) {
    val state by viewModel.collectAsState()

    PokedexList(
        state = state
    )
}

@Composable
fun PokedexList(
    state: PokedexListScreenState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = state.data,
            itemContent = { item ->
                PokedexItemView(
                    item = item
                )
            },
        )
    }
}
