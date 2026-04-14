package com.sarang.torang

import android.se.omapi.Session
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.GsonBuilder
import com.sarang.torang.api.ApiAlarm
import com.sarang.torang.repository.LoginRepository
import com.sarang.torang.session.SessionService
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ApiAlarmTest {

     val tag = "__ApiAlarmTest"

    @Inject lateinit var loginRepository: LoginRepository
    @Inject lateinit var apiAlarm: ApiAlarm
    @Inject lateinit var session: SessionService
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before fun setUp() = runTest {
        hiltRule.inject()
        loginRepository.emailLogin("sry_ang@naver.com","Torang!234")
    }

    @Test fun test() = runTest {
        session.getToken()?.let {
            val result = apiAlarm.findAll(it).body()
            Log.d(tag, GsonBuilder().setPrettyPrinting().create().toJson(result))
        }
    }
}