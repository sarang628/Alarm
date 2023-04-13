package com.sarang.screen_alarm2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class AlarmUiState(
    val isRefreshing: Boolean,
    val list: List<AlarmListItem>,
    val errorMsg: String?,
    val isLoaded: Boolean = false
) {
    fun hasAlarm(): Boolean {
        if (isLoaded && list.isEmpty()) {
            return false
        }
        return true
    }
}

data class AlarmListItem(
    val id: Int,
    val contents: String,
    val otherPictureUrl: String,
    val createDate: String
)