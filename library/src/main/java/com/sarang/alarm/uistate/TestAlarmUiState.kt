package com.sarang.alarm.uistate

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.sarang.alarm.fragment.AlarmUiState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun testRefreshing(): LiveData<AlarmUiState> {
    return MutableLiveData(
        AlarmUiState(
            isRefreshing = true
        )
    )
}


fun testRefreshing1(lifecycleOwner: LifecycleOwner): LiveData<AlarmUiState> {
    val data: MutableLiveData<AlarmUiState> = MutableLiveData(AlarmUiState())

    lifecycleOwner.lifecycleScope.launch {
        while (true) {
            delay(2000)
            data.postValue(
                data.value!!.copy(isRefreshing = !data.value!!.isRefreshing)
            )
        }
    }

    return data
}
