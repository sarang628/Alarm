package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.torang_core.data.model.Alarm
import com.example.torang_core.data.model.AlarmType
import com.example.torang_core.data.model.LoggedInUserData
import com.example.torang_core.data.model.User
import com.sryang.torang_repository.data.entity.LoggedInUserEntity
import com.sryang.torang_repository.repository.AlarmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep
import javax.inject.Inject
import javax.inject.Singleton

/*
@Singleton
class TestAlarmRepositoryImpl @Inject constructor() : AlarmRepository {
    override val isLogin: LiveData<Boolean>
        get() = MutableLiveData(true)

    override suspend fun deleteAlarm() {

    }

    var count = 0;

    override suspend fun loadAlarm1(): ArrayList<com.sryang.torang_core.data.entity.Alarm> {
        withContext(Dispatchers.IO) {
            sleep(1000)
        }

        count = ++count % 3

        if (count == 0)
            throw Exception("서버 접속에 실패하였습니다.")
        else if (count == 1)
            return ArrayList();
        else if (count == 2) {
            val list = ArrayList<Alarm>()
            val alarm = Alarm()
            alarm.alarm_id = 1
            alarm.alarm_type = AlarmType.COMMENT
            alarm.user = User.dummy
            alarm.contents = "가나다라마바사"
            alarm.otherUser = User.dummy
            alarm.other_user_id = 10
            alarm.user_id = 1;
            list.add(alarm);
            return list;
        }
        throw Exception("");
    }

    override fun user(): LiveData<LoggedInUserEntity?> {
        return MutableLiveData()
    }
}*/
