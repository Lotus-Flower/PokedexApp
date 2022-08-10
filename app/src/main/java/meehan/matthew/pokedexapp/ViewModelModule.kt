package meehan.matthew.pokedexapp

import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.MavericksViewModelComponent
import com.airbnb.mvrx.hilt.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PokedexListViewModel::class)
    abstract fun pokedexListViewModelFactory(factory: PokedexListViewModel.Factory): AssistedViewModelFactory<*, *>
}
