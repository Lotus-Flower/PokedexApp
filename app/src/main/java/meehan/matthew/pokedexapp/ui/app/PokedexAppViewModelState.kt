package meehan.matthew.pokedexapp.ui.app

import com.airbnb.mvrx.MavericksState
import meehan.matthew.pokedexapp.navigation.NavigationRoute

data class PokedexAppViewModelState(
    val selectedNavigationButton: NavigationRoute = NavigationRoute.POKEDEX_LIST_ROUTE,
    val isLoading: Boolean = true,
    val errorMessage: String = ""
): MavericksState
