package meehan.matthew.pokedexapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexAppViewModel @Inject constructor(
    private val repository: PokedexRepository
) : ViewModel() {

    init {
        repository.clearLocalPokemonData()

        viewModelScope.launch {
            getAndPersistPokemon()
        }
    }

    private suspend fun getAndPersistPokemon() {
        val pokemonList = repository.getRemotePokemonList()

        pokemonList.forEach {
            repository.persistPokemon(it)
        }
    }

    fun navigateToTopLevelDestination(
        navController: NavController,
        navigationRoute: NavigationRoute
    ) =
        navController.navigate(navigationRoute.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
}
