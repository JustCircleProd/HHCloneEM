package com.justcircleprod.hhcloneem.search.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.VacancyModel
import com.justcircleprod.hhcloneem.core.presentation.theme.Blue
import com.justcircleprod.hhcloneem.core.presentation.theme.Green
import com.justcircleprod.hhcloneem.core.presentation.theme.Grey1
import com.justcircleprod.hhcloneem.core.presentation.theme.Grey3
import com.justcircleprod.hhcloneem.core.presentation.theme.Grey4
import com.justcircleprod.hhcloneem.core.presentation.theme.SFProDisplayFontFamily
import com.justcircleprod.hhcloneem.core.presentation.theme.White
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun VacancyItem(
    vacancyModel: VacancyModel,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(dimensionResource(R.dimen.default_rounded_corner)),
        colors = CardDefaults.cardColors(containerColor = Grey1),
        modifier = modifier
            .fillMaxWidth()
            .clickable {

            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(19.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    if (vacancyModel.lookingNumber != null) {
                        VacancyItemLookingNumberText(vacancyModel.lookingNumber)

                        Spacer(Modifier.height(12.dp))
                    }

                    VacancyItemTitleText(vacancyModel.title)
                }

                VacancyItemLikeButton(vacancyModel.isFavorite)
            }

            if (vacancyModel.salary.short != null) {
                Spacer(Modifier.height(12.dp))

                VacancyItemSalaryText(vacancyModel.salary.short)
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text = vacancyModel.address.town,
                color = White,
                fontSize = 17.sp,
                lineHeight = 20.sp,
                fontFamily = SFProDisplayFontFamily
            )

            Spacer(Modifier.height(5.dp))

            VacancyItemCompanyText(vacancyModel.company)

            Spacer(Modifier.height(12.dp))

            VacancyItemExperienceText(vacancyModel.experience.previewText)

            Spacer(Modifier.height(12.dp))

            VacancyItemPublishedDateText(vacancyModel.publishedDate)

            Spacer(Modifier.height(25.dp))

            VacancyItemResponseButton()
        }
    }
}

@Composable
private fun VacancyItemLookingNumberText(lookingNumber: Int) {
    Text(
        text = pluralStringResource(
            R.plurals.looking_number,
            lookingNumber,
            lookingNumber
        ),
        color = Green,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = SFProDisplayFontFamily
    )
}

@Composable
private fun VacancyItemTitleText(title: String) {
    Text(
        text = title,
        color = White,
        fontSize = 19.sp,
        lineHeight = 23.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = SFProDisplayFontFamily
    )
}

@Composable
private fun VacancyItemLikeButton(isFavourite: Boolean) {
    IconButton(
        modifier = Modifier.size(dimensionResource(R.dimen.default_icon_size)),
        onClick = {

        }
    ) {
        Icon(
            painter = if (isFavourite) painterResource(R.drawable.icon_favourite_filled) else painterResource(
                R.drawable.icon_favourite_outlined
            ),
            contentDescription = if (isFavourite) stringResource(R.string.remove_from_favourite) else stringResource(
                R.string.add_from_favourite
            ),
            tint = if (isFavourite) Blue else Grey4,
            modifier = Modifier.size(dimensionResource(R.dimen.default_icon_size))
        )
    }
}

@Composable
private fun VacancyItemSalaryText(salary: String) {
    Text(
        text = salary,
        color = White,
        fontSize = 24.sp,
        lineHeight = 29.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = SFProDisplayFontFamily
    )
}

@Composable
fun VacancyItemCompanyText(company: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = company,
            color = White,
            fontSize = 17.sp,
            lineHeight = 20.sp,
            fontFamily = SFProDisplayFontFamily
        )

        Spacer(Modifier.width(10.dp))

        Icon(
            painter = painterResource(R.drawable.icon_official_company),
            contentDescription = stringResource(R.string.official_company),
            tint = Grey3,
            modifier = Modifier.size(19.dp)
        )
    }
}

@Composable
private fun VacancyItemExperienceText(experience: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(R.drawable.icon_experience),
            contentDescription = null,
            tint = White,
            modifier = Modifier.size(19.dp)
        )

        Spacer(Modifier.width(10.dp))

        Text(
            text = experience,
            color = White,
            fontSize = 17.sp,
            lineHeight = 20.sp,
            fontFamily = SFProDisplayFontFamily
        )
    }
}

@Composable
private fun VacancyItemPublishedDateText(publishedDateStr: String) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM", Locale("ru"))

    val publishedDate = inputFormat.parse(publishedDateStr) ?: return
    val publishedDateText = outputFormat.format(publishedDate)

    Text(
        text = stringResource(R.string.published_at, publishedDateText),
        color = Grey3,
        fontSize = 17.sp,
        lineHeight = 20.sp,
        fontFamily = SFProDisplayFontFamily
    )
}

@Composable
fun VacancyItemResponseButton() {
    TextButton(
        shape = RoundedCornerShape(60.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Green,
            contentColor = White
        ),
        contentPadding = PaddingValues(vertical = 7.dp),
        modifier = Modifier.fillMaxWidth(),
        onClick = {

        }
    ) {
        Text(
            text = stringResource(R.string.responde),
            maxLines = 1,
            fontSize = 17.sp,
            lineHeight = 22.sp,
            fontFamily = SFProDisplayFontFamily,
        )
    }
}