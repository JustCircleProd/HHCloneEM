package com.justcircleprod.hhcloneem.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.justcircleprod.hhcloneem.presentation.theme.SFProDisplayFontFamily

@Composable
fun Title2(text: String, color: Color, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = color,
        fontFamily = SFProDisplayFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 29.sp,
        modifier = modifier
    )
}

@Composable
fun Title3(text: String, color: Color, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = color,
        fontFamily = SFProDisplayFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 19.sp,
        lineHeight = 23.sp,
        modifier = modifier
    )
}

@Composable
fun Title4(
    text: String,
    color: Color,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = { },
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        maxLines = maxLines,
        fontFamily = SFProDisplayFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        onTextLayout = onTextLayout,
        modifier = modifier
    )
}

@Composable
fun ButtonText1(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontFamily = SFProDisplayFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 19.sp,
        lineHeight = 25.sp,
        modifier = modifier
    )
}

@Composable
fun ButtonText2(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontFamily = SFProDisplayFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 22.sp,
        modifier = modifier
    )
}

@Composable
fun Text1(
    text: String,
    color: Color,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        overflow = overflow,
        maxLines = maxLines,
        fontFamily = SFProDisplayFontFamily,
        fontSize = 17.sp,
        lineHeight = 20.sp,
        modifier = modifier
    )
}

@Composable
fun TabText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        fontFamily = SFProDisplayFontFamily,
        fontSize = 12.sp,
        lineHeight = 13.sp,
        modifier = modifier
    )
}

@Composable
fun NumberText(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        maxLines = 1,
        fontFamily = SFProDisplayFontFamily,
        fontSize = 9.sp,
        lineHeight = 10.sp,
        modifier = modifier
    )
}