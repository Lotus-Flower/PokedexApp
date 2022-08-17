package meehan.matthew.pokedexapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.mvrx.compose.mavericksViewModel
import meehan.matthew.pokedexapp.ui.theme.PokedexAppTheme

@Composable
fun PokedexAppScreen(
    viewModel: PokedexAppViewModel
) {

    PokedexAppTheme {
        PokedexApp(
            onNavigationButtonSelected = { navHostController, navigationRoute ->
                viewModel.navigateToTopLevelDestination(
                    navController = navHostController,
                    navigationRoute = navigationRoute
                )
            }
        )
    }
}

@Composable
fun PokedexApp(
    onNavigationButtonSelected: (NavHostController, NavigationRoute) -> Unit,
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

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
                        onNavigationButtonSelected.invoke(
                            navController,
                            NavigationRoute.POKEDEX_LIST_ROUTE
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
                        onNavigationButtonSelected.invoke(
                            navController,
                            NavigationRoute.POKEDEX_FAVORITES_ROUTE
                        )
                    }
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        Navigator(
            navHostController = navController,
            modifier = Modifier
                .padding(
                    paddingValues = innerPadding
                )
        )
    }
}

@Composable
fun Navigator(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationRoute.POKEDEX_LIST_ROUTE.route,
        modifier = modifier
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

@Preview
@Composable
fun PokedexAppPreview() {
    PokedexAppTheme {
        PokedexApp(
            onNavigationButtonSelected = {
                    _, _ ->
            }
        )
    }
}
