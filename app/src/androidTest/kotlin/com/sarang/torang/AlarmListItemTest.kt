package com.sarang.torang

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.sarang.torang.compose.AlarmListItem
import com.sarang.torang.data1.alarm.AlarmListItemUIState
import com.sryang.torang.ui.TorangTheme
import org.junit.Rule
import org.junit.Test

class AlarmListItemTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun myTest() {
        val contents = "가나다라"
        val uiState =  AlarmListItemUIState(contents = contents,
                                            createdDate = "2025-02-13 11:12:30")
        // Start the app
        composeTestRule.setContent {
            TorangTheme() {
                AlarmListItem(alarmListItem = uiState)
            }
        }

        composeTestRule.onNodeWithTag(testTag = "content").performClick()
        composeTestRule.onNodeWithTag(testTag = "profileImage").performClick()
        composeTestRule.onNodeWithTag(testTag = "reviewImage").performClick()

        composeTestRule.onNodeWithText(text = contents).assertIsDisplayed()
        assert(
            value = composeTestRule.onNodeWithText(text = uiState.transformDate()).isDisplayed(),
                                                   lazyMessage = { "알람 아이템 날짜 포맷 디스플레이 실패" }
        )
    }
}