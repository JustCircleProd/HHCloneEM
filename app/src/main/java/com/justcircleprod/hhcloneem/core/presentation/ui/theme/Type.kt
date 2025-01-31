package com.justcircleprod.hhcloneem.core.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.justcircleprod.hhcloneem.R

val SFProDisplayFontFamily = FontFamily(
    Font(
        resId = R.font.sf_pro_display_regular,
        weight = FontWeight.Normal,
    ),
    Font(
        resId = R.font.sf_pro_display_medium,
        weight = FontWeight.Medium,
    ),
    Font(
        resId = R.font.sf_pro_display_semibold,
        weight = FontWeight.SemiBold
    )
)

val Typography = Typography()