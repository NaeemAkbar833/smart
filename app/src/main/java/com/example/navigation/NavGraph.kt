package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.classsetup.ClassSetupScreen
import com.example.ui.evaluation.AiEvaluationReviewScreen
import com.example.ui.exam.CreateExamScreen
import com.example.ui.home.HomeScreen
import com.example.ui.login.LoginScreen
import com.example.ui.processing.AiEvaluationProcessingScreen
import com.example.ui.profile.ProfileScreen
import com.example.ui.questions.QuestionsScreen
import com.example.ui.result.StudentResultScreen
import com.example.ui.results.ResultsScreen
import com.example.ui.scan.ReviewScanScreen
import com.example.ui.scan.ScanCameraScreen
import com.example.ui.scan.ScanPaperScreen
import com.example.ui.signup.SignupScreen
import com.example.ui.splash.SplashScreen
import com.example.ui.students.StudentListScreen

@Composable
fun SmartPaperNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToSignup = {
                    navController.navigate(Screen.Signup.route)
                },
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Screen.Signup.route) {
            SignupScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                onSignupSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToCreateExam = {
                    navController.navigate(Screen.CreateExam.route)
                },
                onNavigateToClasses = {
                    navController.navigate(Screen.ClassSetup.route)
                },
                onNavigateToResults = {
                    navController.navigate(Screen.Results.route)
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }
        composable(Screen.CreateExam.route) {
            CreateExamScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToClassSetup = {
                    navController.navigate(Screen.ClassSetup.route)
                }
            )
        }
        composable(Screen.ClassSetup.route) {
            ClassSetupScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToQuestions = {
                    navController.navigate(Screen.Questions.route)
                }
            )
        }
        composable(Screen.Questions.route) {
            QuestionsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToStudentList = {
                    navController.navigate(Screen.StudentList.route)
                }
            )
        }
        composable(Screen.StudentList.route) {
            StudentListScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToScanPaper = {
                    navController.navigate(Screen.ScanPaper.route)
                }
            )
        }
        composable(Screen.ScanPaper.route) {
            ScanPaperScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToCamera = {
                    navController.navigate(Screen.ScanCamera.route)
                }
            )
        }
        composable(Screen.ScanCamera.route) {
            ScanCameraScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToReviewScan = {
                    navController.navigate(Screen.ReviewScan.route)
                }
            )
        }
        composable(Screen.ReviewScan.route) {
            ReviewScanScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToEvaluationReview = {
                    navController.navigate(Screen.AiEvaluationReview.route)
                }
            )
        }
        composable(Screen.AiEvaluationReview.route) {
            AiEvaluationReviewScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToProcessing = {
                    navController.navigate(Screen.AiEvaluationProcessing.route)
                }
            )
        }
        composable(Screen.AiEvaluationProcessing.route) {
            AiEvaluationProcessingScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToResult = {
                    navController.navigate(Screen.StudentResult.route)
                }
            )
        }
        composable(Screen.StudentResult.route) {
            StudentResultScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToResults = {
                    navController.navigate(Screen.Results.route)
                }
            )
        }
        composable(Screen.Results.route) {
            ResultsScreen(
                onNavigateToStudentResult = {
                    navController.navigate(Screen.StudentResult.route)
                },
                onNavigateHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigateHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToResults = {
                    navController.navigate(Screen.Results.route)
                },
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
