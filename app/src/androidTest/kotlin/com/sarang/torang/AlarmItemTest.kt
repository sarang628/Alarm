package com.sarang.torang

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.sarang.torang.compose.AlarmItem
import com.sarang.torang.data1.alarm.AlarmListItemUIState
import com.sarang.torang.data1.alarm.transformDate
import com.sryang.torang.ui.TorangTheme
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class AlarmItemTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun clickTest(){
        var onProfileClick: Boolean
        var onContents: Boolean
        composeTestRule.setContent {
            TorangTheme() {
                AlarmItem(alarmListItem = AlarmListItemUIState.Item(),
                    onProfile     = {onProfileClick = true},
                    onContents    = {onContents = true})
            }
        }

        onProfileClick = false
        onContents = false
        composeTestRule.onNodeWithTag(testTag = "content").performClick()
        Assert.assertEquals(true, onContents)
        Assert.assertEquals(false, onProfileClick)

        onProfileClick = false
        onContents = false
        composeTestRule.onNodeWithTag(testTag = "profileImage").performClick()
        Assert.assertEquals(false, onContents)
        Assert.assertEquals(true, onProfileClick)

        onProfileClick = false
        onContents = false
        composeTestRule.onNodeWithTag(testTag = "reviewImage").performClick()
        Assert.assertEquals(true, onContents)
        Assert.assertEquals(false, onProfileClick)
    }

    @Test
    fun displayTest() {
        val contents = "가나다라"
        val uiState =  AlarmListItemUIState.Item(contents = contents,
                                            createdDate = "2025-02-13 11:12:30")

        // Start the app
        composeTestRule.setContent {
            TorangTheme {
                AlarmItem(alarmListItem = uiState)
            }
        }

        composeTestRule.onNodeWithText(text = contents).assertIsDisplayed()
        assert(
            value = composeTestRule.onNodeWithText(text = uiState.transformDate()).isDisplayed(),
                                                   lazyMessage = { "알람 아이템 날짜 포맷 디스플레이 실패" }
        )
    }
}