package meehan.matthew.pokedexapp

data class PokedexItemState(
    val data: PokemonListResponse,
    val favorite: Boolean
)
