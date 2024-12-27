package me.androidbox.mastermeme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import me.androidbox.mastermeme.presentation.EditorScreen
import me.androidbox.mastermeme.presentation.MemeScreen
import me.androidbox.mastermeme.presentation.MemeViewModel
import me.androidbox.mastermeme.presentation.SURFACE_CONTAINER_LOWEST_COLOR
import me.androidbox.mastermeme.presentation.component.MasterMemeAppBar
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        MasterMemeApp()
    }
}

enum class MasterMemeScreen {
    HomeScreen,
    EditorScreen
}

@Composable
fun MasterMemeApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = MasterMemeScreen.valueOf(
        backStackEntry?.destination?.route ?: MasterMemeScreen.HomeScreen.name
    )

    var shouldShowLeaveDialog by remember {
        mutableStateOf(false)
    }

    val memeViewModel = koinViewModel<MemeViewModel>()

    Scaffold(
        topBar = {
            MasterMemeAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUpConfirmation = { shouldShowLeaveDialog = true }
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MasterMemeScreen.HomeScreen.name,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(route = MasterMemeScreen.HomeScreen.name) {
                MemeScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(Color(SURFACE_CONTAINER_LOWEST_COLOR)),
                    viewModel = memeViewModel,
                    onClickMeme = {
                        memeViewModel.selectMeme(it)
                        navController.navigate(MasterMemeScreen.EditorScreen.name)
                    }
                )
            }

            composable(route = MasterMemeScreen.EditorScreen.name) {
                EditorScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(Color(SURFACE_CONTAINER_LOWEST_COLOR)),
                    navController = navController,
                    memeViewModel = memeViewModel,
                    shouldShowLeaveDialog = shouldShowLeaveDialog,
                    onDismissLeaveDialog = {
                        shouldShowLeaveDialog = false
                    },
                    onLeaveEditorScreen = {
                        shouldShowLeaveDialog = false
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}
