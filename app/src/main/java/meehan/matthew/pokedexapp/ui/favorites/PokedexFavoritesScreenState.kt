package meehan.matthew.pokedexapp.ui.favorites

import com.airbnb.mvrx.MavericksState
import meehan.matthew.pokedexapp.ui.list.PokedexItemState

data class PokedexFavoritesScreenState(
    val data: List<PokedexItemState> = emptyList()
) : MavericksState
