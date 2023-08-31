package com.tommyvisic.calorietracker2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tommyvisic.calorietracker2.core.data.Preferences
import com.tommyvisic.calorietracker2.navigation.Route
import com.tommyvisic.calorietracker2.onboarding.presentation.*
import com.tommyvisic.calorietracker2.tracker.presentation.overview.TrackerOverviewScreen
import com.tommyvisic.calorietracker2.tracker.presentation.search.SearchScreen
import com.tommyvisic.calorietracker2.ui.theme.CalorieTracker2Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Entry point for our single-Activity app. This is where all the navigation functionality is
 * defined. I've seen other apps wrap this all up into an App composable which seems cleaner.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shouldShowOnboarding = preferences.loadShouldShowOnboarding()
        setContent {
            CalorieTracker2Theme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) { padding ->
                    NavHost(
                        modifier = Modifier.padding(padding),
                        navController = navController,
                        startDestination =
                        if (shouldShowOnboarding)
                            Route.Welcome
                        else
                            Route.TrackerOverview
                    ) {
                        composable(Route.Welcome) {
                            WelcomeScreen(onNextClick = {
                                navController.navigate(Route.Gender)
                            })
                        }
                        composable(Route.Gender) {
                            GenderScreen(onNextClick = {
                                navController.navigate(Route.Age)
                            })
                        }
                        composable(Route.Age) {
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNextClick =  {
                                    navController.navigate(Route.Height)
                                }
                            )
                        }
                        composable(Route.Height) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick =  {
                                    navController.navigate(Route.Weight)
                                }
                            )
                        }
                        composable(Route.Weight) {
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick =  {
                                    navController.navigate(Route.Activity)
                                }
                            )
                        }
                        composable(Route.Activity) {
                            ActivityLevelScreen(onNextClick = {
                                navController.navigate(Route.Goal)
                            })
                        }
                        composable(Route.Goal) {
                            GoalScreen(onNextClick = {
                                navController.navigate(Route.NutrientGoal)
                            })
                        }
                        composable(Route.NutrientGoal) {
                            NutrientGoalScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.TrackerOverview)
                                }
                            )
                        }
                        composable(Route.TrackerOverview) {
                            TrackerOverviewScreen(
                                onNavigateToSearch = { mealName, day, month, year ->
                                    val route = Route.Search +
                                            "/${mealName}" +
                                            "/${day}" +
                                            "/${month}" +
                                            "/${year}"
                                    navController.navigate(route)
                                }
                            )
                        }
                        composable(
                            route = Route.Search + "/{mealName}/{dayOfMonth}/{month}/{year}",
                            arguments = listOf(
                                navArgument("mealName") {
                                    type = NavType.StringType
                                },
                                navArgument("dayOfMonth") {
                                    type = NavType.IntType
                                },
                                navArgument("month") {
                                    type = NavType.IntType
                                },
                                navArgument("year") {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            val mealName = it.arguments?.getString("mealName")!!
                            val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                            val month = it.arguments?.getInt("month")!!
                            val year = it.arguments?.getInt("year")!!

                            SearchScreen(
                                scaffoldState = scaffoldState,
                                mealTypeText = mealName,
                                dayOfMonth = dayOfMonth,
                                month = month,
                                year = year,
                                onNavigateUp = {
                                    navController.navigateUp()
                                })
                        }
                    }
                }
            }
        }
    }
}