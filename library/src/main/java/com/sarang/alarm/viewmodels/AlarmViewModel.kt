package com.sarang.alarm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarang.alarm.service.GetAlarmUseCase
import com.sarang.alarm.uistate.AlarmUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val alarmService: GetAlarmUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AlarmUiState())
    val uiState = _uiState.asStateFlow()

    init {
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            _uiState.emit(uiState.value.copy(isRefreshing = true))
            try {
                val result = alarmService.getAlarm();
                _uiState.update { it.copy(list = result, isRefreshing = false) }
            } catch (e: Exception) {

            } finally {
                _uiState.update { it.copy(isRefreshing = false) }
            }
        }
    }
}