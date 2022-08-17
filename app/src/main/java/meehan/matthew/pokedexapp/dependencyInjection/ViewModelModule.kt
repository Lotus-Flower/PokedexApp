package meehan.matthew.pokedexapp.dependencyInjection

import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.MavericksViewModelComponent
import com.airbnb.mvrx.hilt.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap
import meehan.matthew.pokedexapp.ui.app.PokedexAppViewModel
import meehan.matthew.pokedexapp.ui.favorites.PokedexFavoritesViewModel
import meehan.matthew.pokedexapp.ui.list.PokedexListViewModel

@Module
@InstallIn(MavericksViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PokedexAppViewModel::class)
    abstract fun pokedexAppViewModelFactory(factory: PokedexAppViewModel.Factory): AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(PokedexListViewModel::class)
    abstract fun pokedexListViewModelFactory(factory: PokedexListViewModel.Factory): AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(PokedexFavoritesViewModel::class)
    abstract fun pokedexFavoritesViewModelFactory(factory: PokedexFavoritesViewModel.Factory): AssistedViewModelFactory<*, *>
}
