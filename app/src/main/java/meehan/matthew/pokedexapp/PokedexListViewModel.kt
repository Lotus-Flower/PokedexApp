package meehan.matthew.pokedexapp

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class PokedexListViewModel @AssistedInject constructor(
    @Assisted state: PokedexListScreenState,
    private val repository: PokedexRepository
) : MavericksViewModel<PokedexListScreenState>(state) {

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        suspend {
            repository.getPokemonList()
        }.execute {
            this.copy(
                data = it.invoke().orEmpty()
            )
        }
    }


    @AssistedFactory
    interface Factory : AssistedViewModelFactory<PokedexListViewModel, PokedexListScreenState> {
        override fun create(state: PokedexListScreenState): PokedexListViewModel
    }

    companion object : MavericksViewModelFactory<PokedexListViewModel, PokedexListScreenState> by hiltMavericksViewModelFactory()
}
