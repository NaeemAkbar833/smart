package com.example.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Signup : Screen("signup")
    object Home : Screen("home")
    object CreateExam : Screen("create_exam")
    object ClassSetup : Screen("class_setup")
    object Questions : Screen("questions")
    object StudentList : Screen("student_list")
    object ScanPaper : Screen("scan_paper")
    object ScanCamera : Screen("scan_camera")
    object ReviewScan : Screen("review_scan")
    object AiEvaluationReview : Screen("ai_evaluation_review")
    object AiEvaluationProcessing : Screen("ai_evaluation_processing")
    object StudentResult : Screen("student_result")
    object Results : Screen("results")
    object Profile : Screen("profile")
}
