package meehan.matthew.pokedexapp.ui.list

import meehan.matthew.pokedexapp.models.PokemonItemData

data class PokedexItemState(
    val data: PokemonItemData,
    val favorite: Boolean
)
