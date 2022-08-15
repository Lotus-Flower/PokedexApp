package meehan.matthew.pokedexapp

data class PokedexItemState(
    val data: PokemonItemResponse,
    val favorite: Boolean,
    val onFavoriteButtonChecked: (checked: Boolean, id: String) -> Unit
)
