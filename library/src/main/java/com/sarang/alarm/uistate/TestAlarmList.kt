package com.sarang.alarm.uistate

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sarang.alarm.fragment.AlarmListItem
import com.sarang.alarm.fragment.AlarmUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

fun testAlarmList(context: Context, lifecycleOwner: LifecycleOwner): LiveData<AlarmUiState> {
    val data: MutableLiveData<AlarmUiState> = MutableLiveData(AlarmUiState())
    lifecycleOwner.lifecycleScope.launch {
        delay(1000)
        data.postValue(
            AlarmUiState(isRefreshing = true)
        )
        delay(2000)
        data.postValue(
            AlarmUiState(list = getTestAlarmListByFile(context))
        )
    }
    return data
}

fun getTestAlarmListByFile(context: Context): List<AlarmListItem> {
    val inputStream = context.assets.open("alarmList.json")
    val inputStreamReader = InputStreamReader(inputStream)
    val bufferReader = BufferedReader(inputStreamReader)

    val gson = Gson();
    val list = gson.fromJson<List<AlarmListItem>>(
        bufferReader,
        object : TypeToken<List<AlarmListItem>>() {}.type
    )
    return list
}







