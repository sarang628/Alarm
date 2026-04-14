package com.sarang.torang.compose

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

typealias AlarmImageLoaderType = @Composable (AlarmImageLoadData) -> Unit

data class AlarmImageLoadData(
    val modifier            : Modifier      = Modifier,
    val model               : Any?          = "",
    val progressSize        : Dp            = 30.dp,
    val errorIconSize       : Dp            = 30.dp,
    val contentScale        : ContentScale  = ContentScale.Fit,
    val contentDescription  : String?       = null,
)

val LocalAlarmImageLoader : ProvidableCompositionLocal<AlarmImageLoaderType> =
    compositionLocalOf<AlarmImageLoaderType> {
        @Composable {
            Image(
                modifier = it.modifier,
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null
            )
        }
    }