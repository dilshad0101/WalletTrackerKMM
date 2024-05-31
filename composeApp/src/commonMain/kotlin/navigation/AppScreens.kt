package navigation

sealed class Screen(val route: String){
    object MainScreen : Screen("Main")
    object Savings: Screen("editor")
    object Expense: Screen("editor0")
}