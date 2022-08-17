package meehan.matthew.pokedexapp.ui.list

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import meehan.matthew.pokedexapp.models.PokemonItemData

class PokedexItemStatePreviewProvider : PreviewParameterProvider<PokedexItemState> {
    override val values: Sequence<PokedexItemState> = sequenceOf(
        PokedexItemState(
            data = PokemonItemData(
                id = "1",
                name = "Bulbasaur",
                types = "Grass, Poison",
                sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
            ),
            favorite = true
        ),
        PokedexItemState(
            data = PokemonItemData(
                id = "4",
                name = "Charmander",
                types = "Fire",
                sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"
            ),
            favorite = false
        )
    )
}
