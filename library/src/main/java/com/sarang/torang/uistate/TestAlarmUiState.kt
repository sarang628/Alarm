package com.sarang.torang.uistate

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.sarang.torang.data1.alarm.AlarmListItemUIState
import com.sarang.torang.data1.alarm.AlarmType
import com.sarang.torang.data1.alarm.AlarmUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


fun testAlarmUiState(context: Context, lifecycleOwner: LifecycleOwner): StateFlow<AlarmUiState> {
    val data = MutableStateFlow<AlarmUiState>(AlarmUiState.Success())
    lifecycleOwner.lifecycleScope.launch {
        val delay = 10000L
        while (true) {
            //리프레시 테스트
//            data.postValue(testRefreshingOn()); delay(delay); data.postValue(testRefreshingOff()); delay(delay);
            //에러메세지 테스트
//            data.postValue(testErrorOn()); delay(delay); data.postValue(testErrorOff()); delay(delay);
            //리스트 테스트
            //data.emit(testListOn(context)); delay(delay);  //data.postValue(testListOff()); delay(delay);
        }
    }
    return data;
}

fun testRefreshingOn(): AlarmUiState {
    return AlarmUiState.Success(isRefreshing = true)
}

fun testRefreshingOff(): AlarmUiState {
    return AlarmUiState.Success(isRefreshing = false)
}

fun testErrorOn(): AlarmUiState {
    return AlarmUiState.Success(errorMsg = "에러 테스트")
}

fun testErrorOff(): AlarmUiState {
    return AlarmUiState.Success()
}

/*fun testListOn(context: Context): AlarmUiState {
    return AlarmUiState(
        list = JsonToObjectGenerator<AlarmListItem>()
            .getListByFile(context, "alarmList.json", AlarmListItem::class.java)
    )
}*/

fun testListOff(): AlarmUiState {
    return AlarmUiState.Success()
}

fun testAlarmListItem(): AlarmListItemUIState.Item {
    return AlarmListItemUIState.Item(
        id = 0,
        contents = "contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents ",
        otherPictureUrl = "otherPictureUrl",
        user = AlarmUser("name"),
        createdDate = "",
        type = AlarmType.LIKE,
        pictureUrl = "",
        reviewId = 1
    )
}

fun testAlarmListItem1(): AlarmListItemUIState {
    return AlarmListItemUIState.Header(title = "TODAY")
}

fun testAlarmListItem2(): AlarmListItemUIState {
    return AlarmListItemUIState.Header(title = "IN THIS WEEK")
}