package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.torang_core.data.model.Alarm
import com.example.torang_core.data.model.LoggedInUserData
import com.example.torang_core.repository.AlarmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestAlarmRepositoryImpl @Inject constructor() : AlarmRepository {
    override val isLogin: LiveData<Boolean>
        get() = MutableLiveData(true)

    override suspend fun deleteAlarm() {

    }

    override suspend fun loadAlarm(): ArrayList<Alarm> {
        withContext(Dispatchers.IO) {
            sleep(1000)
        }
        //throw Exception("서버 접속에 실패하였습니다.")
        return ArrayList();
    }

    override fun user(): LiveData<LoggedInUserData?> {
        return MutableLiveData()
    }
}