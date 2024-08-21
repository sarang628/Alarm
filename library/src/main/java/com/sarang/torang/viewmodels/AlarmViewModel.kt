package com.sarang.torang.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarang.torang.usecase.GetAlarmUseCase
import com.sarang.torang.uistate.AlarmUiState
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
    var uiState by mutableStateOf(AlarmUiState())
        private set

    val isLogin = alarmService.isLogin

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            uiState = uiState.copy(isRefreshing = true)
            try {
                val result = alarmService.getAlarm();
                uiState = uiState.copy(
                    list = result,
                    isRefreshing = false,
                    isEmptyAlarm = result.isEmpty()
                )
            } catch (e: Exception) {

            } finally {
                uiState = uiState.copy(isRefreshing = false)
            }
        }
    }
}