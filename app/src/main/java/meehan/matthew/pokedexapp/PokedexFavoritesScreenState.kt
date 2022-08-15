package meehan.matthew.pokedexapp

import com.airbnb.mvrx.MavericksState

data class PokedexFavoritesScreenState(
    val data: List<PokedexItemState> = emptyList()
) : MavericksState
