package com.sarang.alarm.recyclerview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sarang.alarm.uistate.AlarmListItem


@Composable
fun Alarm(alarmListItem: AlarmListItem?) {

    if (alarmListItem == null)
        return;

    Row(Modifier.padding(start = 8.dp)) {
        AsyncImage(
            model = alarmListItem.otherPictureUrl,
            contentDescription = "",
            Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.height(50.dp), verticalArrangement = Arrangement.Center) {
            Text(text = "${alarmListItem.user?.name}님 이 포스트를 좋아합니다.")
            Text(text = "2주 전")
        }

    }

}