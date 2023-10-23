package com.sarang.alarm.recyclerview

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sarang.alarm.uistate.AlarmListItem


@Composable
fun AlarmListItem(profileServerUrl: String, alarmListItem: AlarmListItem?) {

    if (alarmListItem == null)
        return;

    Row(
        Modifier
            .padding(start = 8.dp)
            .height(65.dp)
    ) {
        Log.d("AlarmListItem", profileServerUrl + alarmListItem.otherPictureUrl)
        AsyncImage(
            model = profileServerUrl + alarmListItem.otherPictureUrl,
            contentDescription = "",
            Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop

        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.height(50.dp), verticalArrangement = Arrangement.Center) {
            Text(text = "${alarmListItem.user?.name}님 이 포스트를 좋아합니다.")
            Text(text = alarmListItem.transformDate())
        }

    }

}