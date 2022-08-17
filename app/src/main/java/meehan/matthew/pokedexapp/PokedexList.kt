package meehan.matthew.pokedexapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import meehan.matthew.pokedexapp.ui.theme.PokedexAppTheme

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

@Preview
@Composable
fun PokedexListPreview() {
    val previewItem = PokedexItemState(
        data = PokemonItemResponse(
            id = "1",
            name = "Bulbasaur",
            types = "Grass, Poison",
            sprite = ""
        ),
        favorite = false,
        onFavoriteButtonChecked = {}
    )

    PokedexAppTheme {
        PokedexList(
            data = listOf(
                previewItem,
                previewItem,
                previewItem
            ),
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
