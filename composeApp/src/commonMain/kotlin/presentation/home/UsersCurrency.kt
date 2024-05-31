package com.app.spendr.presentation.home

import java.util.*


data class UsersCurrency(
    val text: String,
    val symbols : String,
    val iconURL : String
    )
const val defaultFlagIconSize = 42
const val GBP_VAL = "GBP"
const val EUR_VAL = "EUR"
const val INR_VAL = "INR"
const val USD_VAL = "USD"



val POUND = UsersCurrency(text = GBP_VAL,
    symbols = Currency.getInstance(Locale.UK).symbol,
    iconURL = "https://img.icons8.com/fluency/$defaultFlagIconSize/great-britain-circular.png",
    )
val EURO = UsersCurrency(text = EUR_VAL,
    symbols = Currency.getInstance(Locale.GERMANY).symbol,
    iconURL = "https://img.icons8.com/fluency/$defaultFlagIconSize/germany-circular.png"
    )
val INR = UsersCurrency(text = INR_VAL,
    symbols = "\u20B9",
    iconURL = "https://img.icons8.com/fluency/$defaultFlagIconSize/india-circular.png"
    )
val USD = UsersCurrency(
    text = USD_VAL,
    symbols = Currency.getInstance(Locale.US).symbol,
    iconURL = "https://img.icons8.com/fluency/$defaultFlagIconSize/usa-circular.png"
)