package com.sarang.torang.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarang.torang.data1.alarm.AlarmListItemUIState
import com.sarang.torang.uistate.AlarmUiState
import com.sarang.torang.uistate.testAlarmListItem
import com.sarang.torang.uistate.testAlarmListItem1
import com.sarang.torang.uistate.testAlarmListItem2
import com.sarang.torang.viewmodels.AlarmViewModel


typealias LoginScreen = @Composable () -> Unit

@Composable
fun AlarmScreen(alarmViewModel  : AlarmViewModel  = hiltViewModel(),
                onContents      : (Int) -> Unit   = {},
                onProfile       : (Int) -> Unit   = {},
                loginScreen     : LoginScreen     = {}) {
    val uiState : AlarmUiState by alarmViewModel.uiState.collectAsState()

    when(val state = uiState){
        is AlarmUiState.Loading->{
            Box(modifier = Modifier.fillMaxSize()){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        is AlarmUiState.Success->{
            AlarmList(list        = state.list,
                      onContents  = onContents,
                      onProfile   = onProfile)
        }
        is AlarmUiState.Login->{
            loginScreen.invoke()
        }
        is AlarmUiState.EmptyList->{
            Box(modifier = Modifier.fillMaxSize()) {
                Text(modifier = Modifier.align(Alignment.Center), text = "알람이 없습니다.")
            }
        }
    }

}

@Composable
fun AlarmList(modifier    : Modifier                      = Modifier,
              list        : List<AlarmListItemUIState>    = listOf(),
              onContents  : (Int) -> Unit                 = {},
              onProfile   : (Int) -> Unit                 = {}) {
    LazyColumn(modifier) {
        items(list) {
            when(it){
                is AlarmListItemUIState.Header -> {
                    AlarmDateHeader(text = it.title)
                }
                is AlarmListItemUIState.Item -> {
                    AlarmItem(alarmListItem = it,
                              onContents = { onContents.invoke(it.reviewId) },
                              onProfile = { onProfile.invoke(it.otherUserId) })
                }
            }
        }
    }
}

@Composable
fun AlarmDateHeader(text: String) {
    Row(modifier          = Modifier.height(50.dp)
                                    .padding(start = 10.dp, bottom = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.Bottom) {
        Text(modifier = Modifier,
             text = text,
             fontSize = 18.sp,
             fontWeight = FontWeight.Bold,)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAlarmList() {
    AlarmList(
        list = ArrayList<AlarmListItemUIState>().apply {
            add(testAlarmListItem1())
            add(testAlarmListItem())
            add(testAlarmListItem())
            add(testAlarmListItem())
            add(testAlarmListItem())
            add(testAlarmListItem2())
            add(testAlarmListItem())
        }, onProfile = {}, onContents = {}
    )
}

@Preview
@Composable
fun PreviewAlarmDateHeader() {
    AlarmDateHeader(text = "Today")
}