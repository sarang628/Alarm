package com.sarang.screen_alarm2

data class AlarmUiState(
    val list: List<AlarmListItem>
)

data class AlarmListItem(
    val id: Int,
    val contents: String,
    val otherPictureUrl: String,
    val createDate: String
)