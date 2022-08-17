package meehan.matthew.pokedexapp

sealed class ApiResponse<T: Any> {
    data class Success<T: Any>(val data: T): ApiResponse<T>()
    data class Error<T: Any>(val message: String): ApiResponse<T>()
}
