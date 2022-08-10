package meehan.matthew.pokedexapp

import com.airbnb.mvrx.MavericksState

data class PokedexListScreenState(
    val data: List<PokemonListResponse> = emptyList()
) : MavericksState
