package meehan.matthew.pokedexapp.ui.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import meehan.matthew.pokedexapp.models.PokemonItemData
import meehan.matthew.pokedexapp.ui.theme.PokedexAppTheme

@Composable
fun PokedexListView(
    data: List<PokedexItemState>,
    onItemChecked: (Boolean, PokemonItemData) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = data,
            itemContent = { item ->
                PokedexItemView(
                    item = item,
                    onItemChecked = onItemChecked
                )
            }
        )
    }
}

@Preview
@Composable
fun PokedexListPreview() {
    val previewItem = PokedexItemState(
        data = PokemonItemData(
            id = "1",
            name = "Bulbasaur",
            types = "Grass, Poison",
            sprite = ""
        ),
        favorite = false
    )

    PokedexAppTheme {
        PokedexListView(
            data = listOf(
                previewItem,
                previewItem,
                previewItem
            ),
            onItemChecked = { _, _ -> },
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
