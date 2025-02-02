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
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.core.domain.offersAndVacancies.model.OfferModel
import com.justcircleprod.hhcloneem.core.presentation.theme.Green
import com.justcircleprod.hhcloneem.core.presentation.theme.Grey1
import com.justcircleprod.hhcloneem.core.presentation.theme.SFProDisplayFontFamily
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
            val offerType = if (offerModel.id != null) OfferType.getById(offerModel.id) else null

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
                            modifier = Modifier.size(28.dp),
                            tint = offerType.iconColor,
                            contentDescription = null
                        )
                    }
                }

                Spacer(Modifier.height(19.dp))
            } else {
                Spacer(Modifier.height(57.dp))
            }


            var titleTextLineCount by remember { mutableIntStateOf(0) }

            Text(
                text = offerModel.title.trim(),
                color = White,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = SFProDisplayFontFamily,
                maxLines = if (offerModel.buttonText != null) 2 else 3,
                onTextLayout = {
                    titleTextLineCount = it.lineCount
                }
            )

            Spacer(Modifier.height(1.dp))

            if (offerModel.buttonText != null) {
                Text(
                    text = offerModel.buttonText.trim(),
                    color = Green,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = SFProDisplayFontFamily
                )
            } else if (titleTextLineCount == 2) {
                Text(
                    text = "",
                    color = Color.Transparent,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = SFProDisplayFontFamily
                )
            }
        }
    }
}