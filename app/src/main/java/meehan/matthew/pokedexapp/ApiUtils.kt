package meehan.matthew.pokedexapp

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.exception.ApolloException

suspend fun <T : Operation.Data> makeNetworkRequest(
    execute: suspend () -> ApolloResponse<T>
) = try {
    ApiResponse.Success(
        data = execute.invoke().dataAssertNoErrors
    )
} catch (exception: ApolloException) {
    ApiResponse.Error(
        message = exception.message.toString()
    )
}
