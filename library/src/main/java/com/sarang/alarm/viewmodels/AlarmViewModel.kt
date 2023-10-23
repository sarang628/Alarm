package com.sarang.alarm.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarang.alarm.service.AlarmService
import com.sarang.alarm.uistate.AlarmUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    val alarmService: AlarmService
) : ViewModel() {
    fun refresh() {
        viewModelScope.launch {
            _uiState.emit(uiState.value.copy(isRefreshing = true))

            val result = alarmService.getAlarm();
            Log.d("AlarmViewModel", result.toString())

            _uiState.emit(
                uiState.value.copy(
                    list = result,
                    isRefreshing = false
                )
            )
        }
    }

    private val _uiState = MutableStateFlow(AlarmUiState())
    val uiState = _uiState.asStateFlow()

    init {
        refresh()
    }
}