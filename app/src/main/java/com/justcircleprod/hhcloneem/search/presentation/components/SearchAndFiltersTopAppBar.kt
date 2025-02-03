package com.justcircleprod.hhcloneem.search.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.core.presentation.theme.Grey2
import com.justcircleprod.hhcloneem.core.presentation.theme.Grey4
import com.justcircleprod.hhcloneem.core.presentation.theme.SFProDisplayFontFamily
import com.justcircleprod.hhcloneem.core.presentation.theme.White

@Composable
fun SearchAndFiltersTopAppBar(
    @StringRes searchHintStringRes: Int,
    onBackButtonClick: (() -> Unit)? = null,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        var searchText by remember { mutableStateOf("") }
        val focusManager = LocalFocusManager.current

        BasicTextField(
            value = searchText,
            onValueChange = { searchText = it },
            cursorBrush = SolidColor(White),
            textStyle = TextStyle(
                color = White,
                fontSize = 17.sp,
                fontFamily = SFProDisplayFontFamily
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()
                }
            ),
            modifier = Modifier
                .clip(RoundedCornerShape(dimensionResource(R.dimen.default_rounded_corner)))
                .background(Grey2)
                .padding(10.dp)
                .weight(1f)
        ) { innerTextField ->

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (onBackButtonClick == null) {
                    SearchIcon()
                } else {
                    BackButton(onBackButtonClick)
                }

                Spacer(Modifier.width(10.dp))

                Box(contentAlignment = Alignment.CenterStart) {
                    innerTextField()

                    SearchHintText(searchHintStringRes, searchText)
                }
            }
        }

        Spacer(Modifier.width(10.dp))

        FiltersButton()
    }
}

@Composable
fun SearchIcon() {
    Icon(
        painterResource(R.drawable.icon_search),
        tint = Grey4,
        contentDescription = null,
        modifier = Modifier.size(dimensionResource(R.dimen.default_icon_size))
    )
}

@Composable
private fun BackButton(onBackButtonClick: (() -> Unit)) {
    IconButton(
        onClick = onBackButtonClick,
        modifier = Modifier.size(dimensionResource(R.dimen.default_icon_size))
    ) {
        Icon(
            painterResource(R.drawable.icon_back),
            tint = White,
            contentDescription = stringResource(R.string.back),
            modifier = Modifier.size(dimensionResource(R.dimen.default_icon_size))
        )
    }
}

@Composable
private fun SearchHintText(
    @StringRes searchHintStringRes: Int,
    searchText: String
) {
    Text(
        text = stringResource(searchHintStringRes),
        maxLines = 1,
        fontSize = 17.sp,
        overflow = TextOverflow.Ellipsis,
        color = Grey4,
        modifier = Modifier.alpha(if (searchText.isEmpty()) 1f else 0f)
    )
}

@Composable
private fun FiltersButton() {
    FilledIconButton(
        shape = RoundedCornerShape(dimensionResource(R.dimen.filters_button_rounded_corner)),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Grey2
        ),
        modifier = Modifier.size(48.dp),
        onClick = { }
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_filters),
            tint = White,
            contentDescription = stringResource(R.string.filters),
            modifier = Modifier.size(dimensionResource(R.dimen.default_icon_size))
        )
    }
}