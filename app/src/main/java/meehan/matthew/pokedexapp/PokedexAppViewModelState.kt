package meehan.matthew.pokedexapp

import com.airbnb.mvrx.MavericksState

data class PokedexAppViewModelState(
    val selectedNavigationButton: NavigationRoute = NavigationRoute.POKEDEX_LIST_ROUTE,
    val isLoading: Boolean = true,
    val errorMessage: String = ""
): MavericksState
