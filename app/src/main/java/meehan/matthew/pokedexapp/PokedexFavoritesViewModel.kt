package meehan.matthew.pokedexapp

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokedexFavoritesViewModel @AssistedInject constructor(
    @Assisted state: PokedexFavoritesScreenState,
    private val repository: PokedexRepository
) : MavericksViewModel<PokedexFavoritesScreenState>(state) {

    init {
        getPokemonData()
        collectFavoritesUpdates()
    }

    private fun getPokemonData() {
        viewModelScope.launch {
            val favorites = repository.getFavorites().first()

            getFavoritePokemon(favorites)
        }
    }

    private suspend fun getFavoritePokemon(favorites: Set<String>) {
        val data = favorites.mapNotNull { id ->
            repository.getSinglePokemonById(id.toInt())?.let {
                PokedexItemState(
                    data = it,
                    favorite = true,
                    onFavoriteButtonChecked = { _, id ->
                        onFavoriteButtonChecked(id)
                    }
                )
            }
        }.sortedBy {
            it.data.id
        }

        withContext(Dispatchers.Main) {
            this@PokedexFavoritesViewModel.setState {
                this.copy(
                    data = data
                )
            }
        }
    }

    private fun collectFavoritesUpdates() = viewModelScope.launch {
        repository.getFavorites()
            .collect {
                getFavoritePokemon(it)
            }
    }

    private fun onFavoriteButtonChecked(id: String) = viewModelScope.launch {
        repository.removeFavorite(id)
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<PokedexFavoritesViewModel, PokedexFavoritesScreenState> {
        override fun create(state: PokedexFavoritesScreenState): PokedexFavoritesViewModel
    }

    companion object : MavericksViewModelFactory<PokedexFavoritesViewModel, PokedexFavoritesScreenState> by hiltMavericksViewModelFactory()
}
