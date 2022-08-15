package meehan.matthew.pokedexapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.mvrx.compose.mavericksViewModel
import meehan.matthew.pokedexapp.ui.theme.PokedexAppTheme

@Composable
fun PokedexApp(
    viewModel: PokedexAppViewModel
) {

    val navController = rememberNavController()

    PokedexAppTheme {
        Scaffold(
            bottomBar = {
                BottomAppBar {
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.List,
                                contentDescription = stringResource(
                                    id = R.string.pokedex_list
                                )
                            )
                        },
                        selected = true,
                        onClick = {
                            viewModel.navigateToTopLevelDestination(
                                navController = navController,
                                navigationRoute = NavigationRoute.POKEDEX_LIST_ROUTE
                            )
                        }
                    )
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = stringResource(
                                    id = R.string.pokedex_favorites
                                )
                            )
                        },
                        selected = false,
                        onClick = {
                            viewModel.navigateToTopLevelDestination(
                                navController = navController,
                                navigationRoute = NavigationRoute.POKEDEX_FAVORITES_ROUTE
                            )
                        }
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = NavigationRoute.POKEDEX_LIST_ROUTE.route,
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                composable(
                    route = NavigationRoute.POKEDEX_LIST_ROUTE.route
                ) {
                    PokedexListScreen(
                        viewModel = mavericksViewModel()
                    )
                }
                composable(
                    route = NavigationRoute.POKEDEX_FAVORITES_ROUTE.route
                ) {
                    PokedexFavoritesScreen(
                        viewModel = mavericksViewModel()
                    )
                }
            }
        }
    }
}
