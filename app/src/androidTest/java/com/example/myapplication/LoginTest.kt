package com.example.myapplication

import com.example.torang_core.data.dao.LoggedInUserDao
import com.example.torang_core.data.model.LoggedInUserData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class LoginTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Inject
    lateinit var loggedInUserDao: LoggedInUserDao

    @Test
    fun login() {
        runBlocking {
            loggedInUserDao.insert(
                LoggedInUserData(
                    userId = 4
                )
            )
        }
    }

    @Test
    fun logout() {
        runBlocking {
            loggedInUserDao.clear()
        }
    }
}