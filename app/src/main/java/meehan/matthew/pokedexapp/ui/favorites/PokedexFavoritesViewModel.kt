package meehan.matthew.pokedexapp.ui.favorites

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import meehan.matthew.pokedexapp.repository.PokedexRepository
import meehan.matthew.pokedexapp.models.PokemonItemData
import meehan.matthew.pokedexapp.ui.list.PokedexItemState

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
                    favorite = true
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

    fun onFavoriteButtonChecked(item: PokemonItemData) = repository.removeFavorite(
        id = item.id
    )

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<PokedexFavoritesViewModel, PokedexFavoritesScreenState> {
        override fun create(state: PokedexFavoritesScreenState): PokedexFavoritesViewModel
    }

    companion object : MavericksViewModelFactory<PokedexFavoritesViewModel, PokedexFavoritesScreenState> by hiltMavericksViewModelFactory()
}
