package com.neillon.nooroweather.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neillon.nooroweather.R

val poppinsFamily = FontFamily(
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = poppinsFamily,
        fontSize = 12.sp,
        color = TextBodySmall,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Medium
    ),
    bodyMedium = TextStyle(
        fontFamily = poppinsFamily,
        color = TextBody,
        fontSize = 15.sp,
        lineHeight = 22.5.sp,
        fontWeight = FontWeight.Medium
    ),

    headlineSmall = TextStyle(
        fontFamily = poppinsFamily,
        color = TextHeadline,
        fontSize = 14.sp,
        lineHeight = 22.5.sp,
        fontWeight = FontWeight.SemiBold
    ),
    headlineMedium = TextStyle(
        fontFamily = poppinsFamily,
        color = TextHeadline,
        fontSize = 20.sp,
        lineHeight = 45.sp,
        fontWeight = FontWeight.SemiBold
    ),
    headlineLarge = TextStyle(
        fontFamily = poppinsFamily,
        color = TextHeadline,
        fontSize = 28.sp,
        lineHeight = 105.sp,
        fontWeight = FontWeight.Medium
    ),

    titleMedium = TextStyle(
        fontFamily = poppinsFamily,
        color = TextHeadline,
        fontSize = 20.sp,
        lineHeight = 30.sp,
        fontWeight = FontWeight.SemiBold
    ),
    titleLarge = TextStyle(
        fontFamily = poppinsFamily,
        color = TextHeadline,
        fontSize = 60.sp,
        lineHeight = 90.sp,
        fontWeight = FontWeight.Medium
    ),

    // Search
    labelSmall = TextStyle(
        fontFamily = poppinsFamily,
        fontSize = 12.sp,
        color = TextBodySmall,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Normal
    ),
)