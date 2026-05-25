package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.screens.*
import com.example.viewmodel.HomeTeacherViewModel

// Custom backstack routing model for reliable offline state handling
sealed class AppScreen {
    object Splash : AppScreen()
    object Login : AppScreen()
    object Home : AppScreen()
    data class Levels(val subject: String) : AppScreen()
    object Quiz : AppScreen()
    object Result : AppScreen()
    object Profile : AppScreen()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                HomeTeacherApp()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeTeacherApp() {
    val viewModel: HomeTeacherViewModel = viewModel()
    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()

    // Dynamic Navigation Backstack
    val backstack = remember { mutableStateListOf<AppScreen>(AppScreen.Splash) }
    val currentScreen = backstack.lastOrNull() ?: AppScreen.Splash

    // Coordinate Android native back button
    BackHandler(enabled = backstack.size > 1) {
        if (currentScreen is AppScreen.Quiz) {
            // Keep quiz safe unless they hit close icon
        } else {
            backstack.removeAt(backstack.lastIndex)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        AnimatedContent(
            targetState = currentScreen,
            transitionSpec = {
                slideInHorizontally(initialOffsetX = { 1000 }) with
                        slideOutHorizontally(targetOffsetX = { -1000 })
            },
            label = "ScreenTransition"
        ) { screen ->
            val screenModifier = Modifier.padding(innerPadding)

            when (screen) {
                is AppScreen.Splash -> {
                    SplashScreen(
                        onStartLearningClicked = {
                            if (userProfile?.registrationCompleted == true) {
                                backstack.add(AppScreen.Home)
                            } else {
                                backstack.add(AppScreen.Login)
                            }
                        },
                        modifier = screenModifier
                    )
                }

                is AppScreen.Login -> {
                    LoginScreen(
                        onLoginSuccess = {
                            // Clear history of splash/login
                            backstack.clear()
                            backstack.add(AppScreen.Home)
                        },
                        viewModel = viewModel,
                        modifier = screenModifier
                    )
                }

                is AppScreen.Home -> {
                    HomeScreen(
                        onSubjectSelected = { selectedSubject ->
                            backstack.add(AppScreen.Levels(selectedSubject))
                        },
                        onProfileClicked = {
                            backstack.add(AppScreen.Profile)
                        },
                        viewModel = viewModel,
                        modifier = screenModifier
                    )
                }

                is AppScreen.Levels -> {
                    LevelsScreen(
                        subject = screen.subject,
                        onLevelSelected = {
                            backstack.add(AppScreen.Quiz)
                        },
                        onBackClicked = {
                            backstack.removeAt(backstack.lastIndex)
                        },
                        viewModel = viewModel,
                        modifier = screenModifier
                    )
                }

                is AppScreen.Quiz -> {
                    QuizScreen(
                        onBackClicked = {
                            backstack.removeAt(backstack.lastIndex)
                        },
                        onQuizFinished = {
                            // Go to result
                            backstack.add(AppScreen.Result)
                        },
                        viewModel = viewModel,
                        modifier = screenModifier
                    )
                }

                is AppScreen.Result -> {
                    ResultScreen(
                        onBackToLevels = {
                            // Pop quiz & result screen, go back to Levels list
                            if (backstack.size >= 2) {
                                backstack.removeRange(backstack.size - 2, backstack.size)
                            }
                        },
                        viewModel = viewModel,
                        modifier = screenModifier
                    )
                }

                is AppScreen.Profile -> {
                    ProfileScreen(
                        onBackClicked = {
                            if (userProfile?.registrationCompleted == false) {
                                // If logged out, reset to splash
                                backstack.clear()
                                backstack.add(AppScreen.Splash)
                            } else {
                                backstack.removeAt(backstack.lastIndex)
                            }
                        },
                        viewModel = viewModel,
                        modifier = screenModifier
                    )
                }
            }
        }
    }
}
