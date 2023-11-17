package com.sarang.alarm.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarang.alarm.recyclerview.AlarmListItem
import com.sarang.alarm.uistate.AlarmListItem
import com.sarang.alarm.uistate.testAlarmListItem
import com.sarang.alarm.uistate.testAlarmListItem1
import com.sarang.alarm.uistate.testAlarmListItem2
import com.sarang.alarm.viewmodels.AlarmViewModel

@Composable
fun AlarmScreen(
    alarmViewModel: AlarmViewModel = hiltViewModel(),   // 알림 뷰모델
    profileServerUrl: String,                           // 프로필 서버 주소
) {
    val uiState by alarmViewModel.uiState.collectAsState()
    val list = uiState.convertDate()
    val isLogin by alarmViewModel.isLogin.collectAsState(initial = false)

    if (isLogin) {
        AlarmList(list, profileServerUrl)
    } else {
        Box(Modifier.fillMaxSize()) {
            Text(text = "로그인을 해주세요.", Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun AlarmList(
    list: List<AlarmListItem>,
    profileServerUrl: String
) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(list.size) {
            if (list[it].indexDate.isNotEmpty()) {
                AlarmListDateItem(list[it].indexDate)
            } else {
                AlarmListItem(
                    profileServerUrl = profileServerUrl,
                    alarmListItem = list[it]
                )
            }
        }
    }
}

@Composable
fun AlarmListDateItem(text: String) {
    Row(
        Modifier
            .height(50.dp)
            .padding(start = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            modifier = Modifier,
            text = text,
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
fun PreviewAlarmList() {
    AlarmList(
        list = ArrayList<AlarmListItem>().apply {
            add(testAlarmListItem1())
            add(testAlarmListItem())
            add(testAlarmListItem())
            add(testAlarmListItem())
            add(testAlarmListItem())
            add(testAlarmListItem2())
            add(testAlarmListItem())
        },
        profileServerUrl = ""
    )
}

@Preview
@Composable
fun PreviewAlarmListDateItem() {
    AlarmListDateItem(text = "Today")
}