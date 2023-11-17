package com.sarang.alarm.service

import com.sarang.alarm.uistate.AlarmListItem
import kotlinx.coroutines.flow.Flow

interface GetAlarmUseCase {
    suspend fun getAlarm(): List<AlarmListItem>
    val isLogin : Flow<Boolean>
}