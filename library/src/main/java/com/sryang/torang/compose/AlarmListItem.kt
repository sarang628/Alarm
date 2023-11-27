package com.sryang.torang.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sryang.torang.uistate.AlarmListItem
import com.sryang.torang.uistate.testAlarmListItem


@Composable
fun AlarmListItem(profileServerUrl: String, alarmListItem: AlarmListItem) {
    Row(
        Modifier
            .padding(start = 8.dp)
            .height(65.dp)
    ) {
        AsyncImage(
            model = profileServerUrl + alarmListItem.otherPictureUrl,
            contentDescription = "",
            Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color(0x11000000)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.height(50.dp), verticalArrangement = Arrangement.Center) {
            Text(text = "${alarmListItem.user?.name}님 이 포스트를 좋아합니다.")
            Text(text = alarmListItem.transformDate())
        }
    }
}

@Preview
@Composable
fun PreviewAlarmListItem() {
    AlarmListItem(profileServerUrl = "", alarmListItem = testAlarmListItem())

}