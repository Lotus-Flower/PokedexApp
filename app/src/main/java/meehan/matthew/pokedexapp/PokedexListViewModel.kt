package meehan.matthew.pokedexapp

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokedexListViewModel @AssistedInject constructor(
    @Assisted state: PokedexListScreenState,
    private val repository: PokedexRepository
) : MavericksViewModel<PokedexListScreenState>(state) {

    init {
        getPokemonData()
    }

    private fun getPokemonData() = viewModelScope.launch {
        combine(repository.getLocalPokemonList(), repository.getFavorites()) { pokemonList, favorites ->
            val data = pokemonList.map { pokemonItem ->
                PokedexItemState(
                    data = pokemonItem,
                    favorite = favorites.any { favorite ->
                         favorite.id == pokemonItem.id
                    },
                    onFavoriteButtonChecked = { checked ->
                        onFavoriteButtonChecked(
                            checked = checked,
                            id = pokemonItem.id
                        )
                    }
                )
            }.sortedBy {
                it.data.id.toInt()
            }

            setLoadedState(data)
        }.collect()
    }

    private fun onFavoriteButtonChecked(checked: Boolean, id: String) = viewModelScope.launch {
        when (checked) {
            true -> repository.addFavorite(
                id = id
            )
            false -> repository.removeFavorite(
                id = id
            )
        }
    }

    private fun setLoadedState(data: List<PokedexItemState>) = setState {
        this.copy(
            data = data
        )
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<PokedexListViewModel, PokedexListScreenState> {
        override fun create(state: PokedexListScreenState): PokedexListViewModel
    }

    companion object : MavericksViewModelFactory<PokedexListViewModel, PokedexListScreenState> by hiltMavericksViewModelFactory()
}
