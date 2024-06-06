package com.sryang.torang.uistate

import android.os.Build
import androidx.annotation.RequiresApi
import com.sryang.torang.data1.alarm.AlarmListItem
import com.sryang.torang.data1.alarm.AlarmType
import com.sryang.torang.data1.alarm.AlarmUser
import com.sryang.torang.util.convertDate
import java.util.*

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
) {
    fun hasAlarm(): Boolean {
        if (isLoaded && list.isEmpty()) {
            return false
        }
        return true
    }

    //데이터 시, 분, 전 변환
    @RequiresApi(Build.VERSION_CODES.N)
    fun convertDate(): List<AlarmListItem> {
        return convertDate(list)
    }
}


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