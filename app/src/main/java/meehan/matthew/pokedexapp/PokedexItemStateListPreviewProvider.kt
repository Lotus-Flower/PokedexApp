package meehan.matthew.pokedexapp

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class PokedexItemStateListPreviewProvider : PreviewParameterProvider<List<PokedexItemState>> {
    private val previewItem = PokedexItemState(
        data = PokemonItemResponse(
            id = "1",
            name = "Bulbasaur",
            types = "Grass, Poison",
            sprite = ""
        ),
        favorite = false,
        onFavoriteButtonChecked = {}
    )

    override val values: Sequence<List<PokedexItemState>> =
        sequenceOf(
            listOf(),
            listOf(
                previewItem,
                previewItem,
                previewItem
            )
        )
}
