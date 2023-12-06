package com.sryang.torang.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.sryang.library.pullrefresh.PullToRefreshLayout
import com.sryang.library.pullrefresh.RefreshIndicatorState
import com.sryang.library.pullrefresh.rememberPullToRefreshState
import com.sryang.torang.data.alarm.AlarmListItem
import com.sryang.torang.uistate.testAlarmListItem
import com.sryang.torang.uistate.testAlarmListItem1
import com.sryang.torang.uistate.testAlarmListItem2
import com.sryang.torang.viewmodels.AlarmViewModel

@Composable
fun AlarmScreen(
    alarmViewModel: AlarmViewModel = hiltViewModel(),   // 알림 뷰모델
) {
    val uiState by alarmViewModel.uiState.collectAsState()
    val list = uiState.convertDate()
    val isLogin by alarmViewModel.isLogin.collectAsState(initial = false)

    if (isLogin) {
        AlarmList(
            isRefresh = uiState.isRefreshing,
            list = list, onRefresh = {
                alarmViewModel.refresh()
            })
    } else {
        Box(Modifier.fillMaxSize()) {
            Text(text = "로그인을 해주세요.", Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun AlarmList(
    isRefresh: Boolean = false,
    list: List<AlarmListItem>,
    onRefresh: () -> Unit
) {
    val state = rememberPullToRefreshState()

    LaunchedEffect(key1 = isRefresh, block = {
        if (isRefresh) {
            state.updateState(RefreshIndicatorState.Refreshing)
        } else {
            state.updateState(RefreshIndicatorState.Default)
        }
    })

    PullToRefreshLayout(pullRefreshLayoutState = state, onRefresh = onRefresh, refreshThreshold = 80) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(list.size) {
                if (list[it].indexDate.isNotEmpty()) {
                    AlarmListDateItem(list[it].indexDate)
                } else {
                    AlarmListItem(alarmListItem = list[it])
                }
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
        }, onRefresh = {}
    )
}

@Preview
@Composable
fun PreviewAlarmListDateItem() {
    AlarmListDateItem(text = "Today")
}