package com.sarang.alarm.service

import com.sarang.alarm.uistate.AlarmListItem

interface AlarmService {
    suspend fun getAlarm(): List<AlarmListItem>
}