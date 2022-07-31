package com.example.myapplication

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import com.sarang.screen_alarm2.AlarmFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AlarmScreenFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun testEventFragment() {
        // The "fragmentArgs" argument is optional.
        val fragmentArgs = bundleOf("selectedListItem" to 100)
        //val scenario = launchFragmentInContainer<AlarmFragment>(fragmentArgs)
        val scenario = launchFragmentInHiltContainer<AlarmFragment>(
            fragmentArgs = fragmentArgs
        )
    }

    @Test
    fun resumeTest() {
        val scenario = launchFragmentInContainer<AlarmFragment>(
            initialState = Lifecycle.State.RESUMED
        )

        // EventFragment has gone through onAttach(), but not onCreate().
        // Verify the initial state.
        scenario.moveToState(Lifecycle.State.RESUMED)
        // EventFragment moves to CREATED -> STARTED -> RESUMED.
    }

}