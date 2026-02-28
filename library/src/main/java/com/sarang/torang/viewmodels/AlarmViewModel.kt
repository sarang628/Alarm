package com.sarang.torang.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarang.torang.data1.alarm.AlarmListItemUIState
import com.sarang.torang.uistate.AlarmUiState
import com.sarang.torang.usecase.GetAlarmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val alarmService: GetAlarmUseCase,
) : ViewModel() {
    private var alarmList : MutableStateFlow<List<AlarmListItemUIState>> = MutableStateFlow(emptyList())
    private var loading : MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLogin = alarmService.isLogin
    val uiState: StateFlow<AlarmUiState> =
        combine(
            alarmService.isLogin,
            alarmList,
            loading
        ) { isLogin, alarmList, loading ->
            if(!isLogin)
                AlarmUiState.Login()
            else if(loading)
                AlarmUiState.Loading()
            else if(alarmList.isEmpty())
                AlarmUiState.EmptyList()
            else
                AlarmUiState.Success(list = alarmList)
        }.stateIn(scope = viewModelScope,
                  started = SharingStarted.WhileSubscribed(5000),
                  initialValue = AlarmUiState.Loading())

    init {
        refresh()
        // 로그아웃에서 로그인 상태로 변경 시 알람 리스트를 새로고침
        viewModelScope.launch {
            isLogin.distinctUntilChanged().collect {
                if (it) refresh()
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            try {
                alarmList.value = alarmService.getAlarm()
                loading.value = false
            }catch (e : Exception){

            }
        }
    }
}