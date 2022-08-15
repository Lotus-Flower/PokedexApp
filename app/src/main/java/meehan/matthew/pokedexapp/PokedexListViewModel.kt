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

class PokedexListViewModel @AssistedInject constructor(
    @Assisted state: PokedexListScreenState,
    private val repository: PokedexRepository
) : MavericksViewModel<PokedexListScreenState>(state) {

    init {
        getPokemonData()
        collectFavoritesUpdates()
    }

    private fun getPokemonData() {
        viewModelScope.launch {
            val pokemonList = repository.getPokemonList()
            val favorites = repository.getFavorites().first()

            val data = pokemonList.map { pokemon ->
                PokedexItemState(
                    data = pokemon,
                    favorite = favorites.contains(pokemon.id),
                    onFavoriteButtonChecked = { checked, id ->
                        onFavoriteButtonChecked(checked, id)
                    }
                )
            }

            withContext(Dispatchers.Main) {
                this@PokedexListViewModel.setState {
                    this.copy(
                        data = data
                    )
                }
            }
        }
    }

    private fun collectFavoritesUpdates() = viewModelScope.launch {
        repository.getFavorites().execute { favorites ->
            val list = mutableListOf<PokedexItemState>()
            this.data.forEach {
                list.add(
                    it.copy(
                        favorite = favorites.invoke()?.contains(it.data.id) ?: false
                    )
                )
            }
            this.copy(
                data = list
            )
        }
    }

    private fun onFavoriteButtonChecked(checked: Boolean, id: String) = viewModelScope.launch {
        when (checked) {
            true -> repository.addFavorite(id)
            false -> repository.removeFavorite(id)
        }
    }


    @AssistedFactory
    interface Factory : AssistedViewModelFactory<PokedexListViewModel, PokedexListScreenState> {
        override fun create(state: PokedexListScreenState): PokedexListViewModel
    }

    companion object : MavericksViewModelFactory<PokedexListViewModel, PokedexListScreenState> by hiltMavericksViewModelFactory()
}
