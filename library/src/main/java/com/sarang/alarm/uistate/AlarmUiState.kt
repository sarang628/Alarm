package com.sarang.alarm.uistate

data class AlarmUiState(
    val isRefreshing: Boolean,
    val list: List<AlarmListItem>,
    val errorMsg: String?,
    val isLoaded: Boolean = false,
    val isLogin: Boolean = false
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