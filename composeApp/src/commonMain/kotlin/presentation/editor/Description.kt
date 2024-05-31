package com.app.spendr.presentation.editor


sealed class Description(val text: String) {
    data object Friend : Description("Friend")
    data object Family : Description("Family")
    data object Salary : Description("Salary")
    data object Reward : Description("Reward")
    data object Online : Description("Online")
    data object Rent : Description("Rent")
    data object Other : Description("Other")
    data object Food : Description("Food")
    data object Shopping : Description("Shopping")

    companion object {
        val descriptions: List<String> = listOf(
            "Friend",
            "Family",
            "Salary",
            "Reward",
            "Online",
           "Rent",
            "Other",
            "Food",
            "Shopping"
        )
    }
}

