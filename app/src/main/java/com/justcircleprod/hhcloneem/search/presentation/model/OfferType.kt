package com.justcircleprod.hhcloneem.search.presentation.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.core.presentation.theme.Blue
import com.justcircleprod.hhcloneem.core.presentation.theme.DarkBlue
import com.justcircleprod.hhcloneem.core.presentation.theme.DarkGreen
import com.justcircleprod.hhcloneem.core.presentation.theme.Green

enum class OfferType(
    private val id: String,
    val iconBackgroundCardColor: Color,
    val iconColor: Color,
    @DrawableRes val iconDrawableRes: Int
) {
    NEAR_VACANCIES(
        id = "near_vacancies",
        iconBackgroundCardColor = DarkBlue,
        iconColor = Blue,
        iconDrawableRes = R.drawable.icon_star
    ),
    LEVEL_UP_RESUME(
        id = "level_up_resume",
        iconBackgroundCardColor = DarkGreen,
        iconColor = Green,
        iconDrawableRes = R.drawable.icon_star
    ),
    TEMPORARY_JOB(
        id = "temporary_job",
        iconBackgroundCardColor = DarkGreen,
        iconColor = Green,
        iconDrawableRes = R.drawable.icon_vacancies
    );

    companion object {
        fun getById(id: String): OfferType? = entries.find { it.id == id }
    }
}