package com.sryang.torang.uistate

import com.sryang.torang.data1.alarm.AlarmListItem
import com.sryang.torang.data1.alarm.AlarmType
import com.sryang.torang.data1.alarm.AlarmUser
import com.sryang.torang.util.convertDate

/** 알림 UiState */
data class AlarmUiState(
    // 스와이프 리프레시 갱신
    val isRefreshing: Boolean = false,
    // 얼림 리스트
    val list: List<AlarmListItem> = ArrayList(),
    // 에러메세지
    val errorMsg: String? = null,
    val isLoaded: Boolean = false,
    val isLogin: Boolean = false,
    val isEmptyAlarm: Boolean = false,
) {
    fun hasAlarm(): Boolean {
        if (isLoaded && list.isEmpty()) {
            return false
        }
        return true
    }
}

//데이터 시, 분, 전 변환
val AlarmUiState.convertedDateList get() = convertDate(list)


fun testAlarmListItem(): AlarmListItem {
    return AlarmListItem(
        id = 0,
        contents = "contents",
        otherPictureUrl = "otherPictureUrl",
        user = AlarmUser("name"),
        createdDate = "",
        type = AlarmType.LIKE,
        pictureUrl = "",
        reviewId = 1
    )
}

fun testAlarmListItem1(): AlarmListItem {
    return AlarmListItem(
        id = 0,
        contents = "contents",
        otherPictureUrl = "otherPictureUrl",
        user = AlarmUser("name"),
        createdDate = "",
        indexDate = "TODAY",
        type = AlarmType.LIKE,
        reviewId = 1
    )
}

fun testAlarmListItem2(): AlarmListItem {
    return AlarmListItem(
        id = 0,
        contents = "contents",
        otherPictureUrl = "otherPictureUrl",
        user = AlarmUser("name"),
        createdDate = "",
        indexDate = "IN THIS WEEK",
        type = AlarmType.LIKE,
        reviewId = 1
    )
}