package com.justcircleprod.hhcloneem.presentation.components

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.domain.offerAndVacancy.model.VacancyModel
import com.justcircleprod.hhcloneem.presentation.theme.Blue
import com.justcircleprod.hhcloneem.presentation.theme.Green
import com.justcircleprod.hhcloneem.presentation.theme.Grey1
import com.justcircleprod.hhcloneem.presentation.theme.Grey3
import com.justcircleprod.hhcloneem.presentation.theme.Grey4
import com.justcircleprod.hhcloneem.presentation.theme.White
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun VacancyItem(
    vacancyModel: VacancyModel,
    onClick: () -> Unit,
    onLikeButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(dimensionResource(R.dimen.default_rounded_corner)),
        colors = CardDefaults.cardColors(containerColor = Grey1),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.default_rounded_corner)))
            .clickable { onClick() }
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

                VacancyItemLikeButton(vacancyModel.isFavorite, onLikeButtonClick)
            }

            if (vacancyModel.salary.short != null) {
                Spacer(Modifier.height(12.dp))

                VacancyItemSalaryText(vacancyModel.salary.short)
            }

            Spacer(Modifier.height(12.dp))

            VacancyItemTownText(vacancyModel.address.town)

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
    Title4(
        text = pluralStringResource(
            R.plurals.looking_number,
            lookingNumber,
            lookingNumber
        ),
        color = Green
    )
}

@Composable
private fun VacancyItemTitleText(title: String) {
    Title3(
        text = title.trim(),
        color = White
    )
}

@Composable
private fun VacancyItemLikeButton(isFavourite: Boolean, onClick: () -> Unit) {
    ScalableIconButton(
        onClick = onClick,
        modifier = Modifier.size(dimensionResource(R.dimen.default_icon_size))
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
    Title2(
        text = salary.trim(),
        color = White
    )
}

@Composable
private fun VacancyItemTownText(town: String) {
    Text1(
        text = town.trim(),
        color = White
    )
}

@Composable
private fun VacancyItemCompanyText(company: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text1(
            text = company.trim(),
            color = White
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

        Text1(
            text = experience.trim(),
            color = White
        )
    }
}

@Composable
private fun VacancyItemPublishedDateText(publishedDateStr: String) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM", Locale("ru"))

    val publishedDate = inputFormat.parse(publishedDateStr) ?: return
    val publishedDateText = outputFormat.format(publishedDate)

    Text1(
        text = stringResource(R.string.published_at, publishedDateText),
        color = Grey3
    )
}

@Composable
fun VacancyItemResponseButton() {
    ScalableTextButton(
        shape = RoundedCornerShape(60.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Green,
            contentColor = White
        ),
        contentPadding = PaddingValues(vertical = 7.dp),
        onClick = { },
        modifier = Modifier.fillMaxWidth()
    ) {
        ButtonText2(text = stringResource(R.string.responde))
    }
}