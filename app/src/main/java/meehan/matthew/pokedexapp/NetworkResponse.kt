package meehan.matthew.pokedexapp

sealed class NetworkResponse {
    object Error : NetworkResponse()
    data class Success<T>(
        val data: T
    ) : NetworkResponse()
}
