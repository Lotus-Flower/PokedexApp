package meehan.matthew.pokedexapp

import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://dex-server.herokuapp.com/")
    .build()
