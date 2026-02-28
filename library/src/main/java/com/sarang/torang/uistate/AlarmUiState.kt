package com.sarang.torang.uistate

import com.sarang.torang.data1.alarm.AlarmListItemUIState

/** 알림 UiState */
sealed interface AlarmUiState{
    class Loading   : AlarmUiState
    class Login     : AlarmUiState
    class EmptyList : AlarmUiState
    class Success(val isRefreshing: Boolean             = false,
                  val list: List<AlarmListItemUIState>  = emptyList(),
                  val errorMsg: String?                 = null) : AlarmUiState
}