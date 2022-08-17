package meehan.matthew.pokedexapp

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class PokedexAppViewModel @AssistedInject constructor(
    @Assisted state: PokedexAppViewModelState,
    private val repository: PokedexRepository
) : MavericksViewModel<PokedexAppViewModelState>(state) {

    init {
        setLoadingState()

        repository.clearLocalPokemonData()
        viewModelScope.launch {
            getAndPersistPokemon()
        }
    }

    private suspend fun getAndPersistPokemon() {
        when (val response = repository.getAndPersistRemotePokemonList()) {
            is ApiResponse.Error -> setErrorState(response.message)
            is ApiResponse.Success -> setLoadedState()
        }
    }

    private fun setLoadingState() = setState {
        this.copy(
            isLoading = true,
            errorMessage = ""
        )
    }

    private fun setErrorState(message: String) = setState {
        this.copy(
            isLoading = false,
            errorMessage = message
        )
    }

    private fun setLoadedState() = setState {
        this.copy(
            isLoading = false,
            errorMessage = ""
        )
    }

    private fun setNavigationButtonSelectedState(navigationRoute: NavigationRoute) = setState {
        this.copy(
            selectedNavigationButton = navigationRoute
        )
    }

    fun navigateToTopLevelDestination(
        navController: NavController,
        navigationRoute: NavigationRoute
    ) {
        navController.navigate(navigationRoute.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        setNavigationButtonSelectedState(navigationRoute)
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<PokedexAppViewModel, PokedexAppViewModelState> {
        override fun create(state: PokedexAppViewModelState): PokedexAppViewModel
    }

    companion object : MavericksViewModelFactory<PokedexAppViewModel, PokedexAppViewModelState> by hiltMavericksViewModelFactory()
}
