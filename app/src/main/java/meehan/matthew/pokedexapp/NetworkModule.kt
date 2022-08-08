package meehan.matthew.pokedexapp

import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesApollo() = ApolloClient.Builder()
        .serverUrl("https://dex-server.herokuapp.com/")
        .build()

}
