package meehan.matthew.pokedexapp

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PokedexList(
    data: List<PokedexItemState>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = data,
            itemContent = { item ->
                PokedexItemView(
                    item = item
                )
            }
        )
    }
}
