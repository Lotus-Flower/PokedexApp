package meehan.matthew.pokedexapp

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokedexAppViewModel @Inject constructor() : ViewModel() {
    fun navigateToTopLevelDestination(
        navController: NavController,
        navigationRoute: NavigationRoute
    ) =
        navController.navigate(navigationRoute.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
}
