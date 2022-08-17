package meehan.matthew.pokedexapp

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class PokedexItemStatePreviewProvider : PreviewParameterProvider<PokedexItemState> {
    override val values: Sequence<PokedexItemState> = sequenceOf(
        PokedexItemState(
            data = PokemonItemResponse(
                id = "1",
                name = "Bulbasaur",
                types = "Grass, Poison",
                sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
            ),
            favorite = true,
            onFavoriteButtonChecked = {}
        ),
        PokedexItemState(
            data = PokemonItemResponse(
                id = "4",
                name = "Charmander",
                types = "Fire",
                sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"
            ),
            favorite = false,
            onFavoriteButtonChecked = {}
        )
    )
}
