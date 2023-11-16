package com.sarang.alarm.service

import com.sarang.alarm.uistate.AlarmListItem

interface GetAlarmUseCase {
    suspend fun getAlarm(): List<AlarmListItem>
}