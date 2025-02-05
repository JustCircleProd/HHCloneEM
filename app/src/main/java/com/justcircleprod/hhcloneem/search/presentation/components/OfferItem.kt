package com.justcircleprod.hhcloneem.search.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.OfferModel
import com.justcircleprod.hhcloneem.core.presentation.components.Title4
import com.justcircleprod.hhcloneem.core.presentation.theme.Green
import com.justcircleprod.hhcloneem.core.presentation.theme.Grey1
import com.justcircleprod.hhcloneem.core.presentation.theme.White
import com.justcircleprod.hhcloneem.search.presentation.model.OfferType

@Composable
fun OfferItem(offerModel: OfferModel) {
    val uriHandler = LocalUriHandler.current

    Card(
        shape = RoundedCornerShape(dimensionResource(R.dimen.default_rounded_corner)),
        colors = CardDefaults.cardColors(containerColor = Grey1),
        modifier = Modifier
            .width(158.dp)
            .clickable {
                uriHandler.openUri(offerModel.link)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 12.dp)
        ) {
            OfferItemIcon(offerModel.id)

            var titleTextLineCount by remember { mutableIntStateOf(0) }

            OfferItemTitleText(
                title = offerModel.title,
                maxLines = if (offerModel.buttonText != null) 2 else 3,
                onTextLayout = {
                    titleTextLineCount = it.lineCount
                }
            )

            Spacer(Modifier.height(1.dp))

            OfferItemButton(
                buttonText = offerModel.buttonText,
                offerTitleTextLineCount = titleTextLineCount
            )
        }
    }
}

@Composable
fun OfferItemIcon(offerModelId: String?) {
    // Get offerType by offerId or assign null if offerId is null
    val offerType = if (offerModelId != null) OfferType.getById(offerModelId) else null

    if (offerType != null) {
        Card(
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = offerType.iconBackgroundCardColor),
            modifier = Modifier.size(38.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(offerType.iconDrawableRes),
                    modifier = Modifier.size(dimensionResource(R.dimen.default_icon_size)),
                    tint = offerType.iconColor,
                    contentDescription = null
                )
            }
        }

        Spacer(Modifier.height(19.dp))
    } else {
        Spacer(Modifier.height(57.dp))
    }
}

@Composable
private fun OfferItemTitleText(
    title: String,
    maxLines: Int,
    onTextLayout: (TextLayoutResult) -> Unit
) {
    Title4(
        text = title.trim(),
        color = White,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun OfferItemButton(buttonText: String?, offerTitleTextLineCount: Int) {
    if (buttonText != null) {
        Title4(
            text = buttonText.trim(),
            color = Green,
            maxLines = 1
        )
    } else if (offerTitleTextLineCount == 2) { // to keep the OfferItems the same size
        Title4(
            text = "",
            color = Color.Transparent,
            maxLines = 1
        )
    }
}