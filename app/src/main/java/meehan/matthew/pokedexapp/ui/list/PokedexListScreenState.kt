package meehan.matthew.pokedexapp.ui.list

import com.airbnb.mvrx.MavericksState

data class PokedexListScreenState(
    val data: List<PokedexItemState> = emptyList()
) : MavericksState
