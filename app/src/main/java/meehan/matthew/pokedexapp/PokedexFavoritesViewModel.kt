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
        getFavoritePokemon()
    }

    private fun getFavoritePokemon() = viewModelScope.launch {
        repository.getFavorites().collect { pokemonList ->
            val data = pokemonList.map { pokemonItem ->
                PokedexItemState(
                    data = pokemonItem,
                    favorite = true,
                    onFavoriteButtonChecked = { _ ->
                        onFavoriteButtonChecked(
                            id = pokemonItem.id
                        )
                    }
                )
            }.sortedBy {
                it.data.id.toInt()
            }

            withContext(Dispatchers.Main) {
                this@PokedexFavoritesViewModel.setState {
                    this.copy(
                        data = data
                    )
                }
            }
        }
    }

    private fun onFavoriteButtonChecked(id: String) = repository.removeFavorite(
        id = id
    )

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<PokedexFavoritesViewModel, PokedexFavoritesScreenState> {
        override fun create(state: PokedexFavoritesScreenState): PokedexFavoritesViewModel
    }

    companion object : MavericksViewModelFactory<PokedexFavoritesViewModel, PokedexFavoritesScreenState> by hiltMavericksViewModelFactory()
}
