package com.sarang.torang.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.sarang.torang.compose.LocalAlarmImageLoader
import com.sarang.torang.data1.alarm.AlarmListItemUIState
import com.sarang.torang.data1.alarm.transformDate
import com.sarang.torang.uistate.testAlarmListItem


@Composable
fun AlarmItem(alarmListItem : AlarmListItemUIState.Item = AlarmListItemUIState.Item(),
              onContents    : () -> Unit    = {},
              onProfile     : () -> Unit    = {}) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()
                                        .clickable { onProfile.invoke() }
                                        .padding(start = 8.dp, end = 8.dp)
                                        .heightIn(min = 65.dp),
                    constraintSet = alarmListItemConstraintSet()) {
        LocalAlarmImageLoader.current(AlarmImageLoadData(model                = alarmListItem.otherPictureUrl,
                                                              contentDescription   = null,
                                                              contentScale         = ContentScale.Crop,
                                                              modifier             = Modifier.layoutId("image")
                                                                                              .testTag("profileImage")
                                                                                              .size(45.dp)
                                                                                              .clip(CircleShape)
                                                                                              .clickable{ onProfile.invoke() }
                                                                                              .background(Color(0x11000000)))
        )

        Column(modifier = Modifier.layoutId("content")
                                  .testTag("content")
                                  .width(0.dp)
                                  .heightIn(min = 50.dp)
                                  .clickable { onContents.invoke() },
               verticalArrangement = Arrangement.Center) {
            Text(modifier = Modifier.fillMaxWidth(),
                 text = alarmListItem.contents,
                 fontSize = 14.sp,
                 lineHeight = 16.sp)
            Text(text = alarmListItem.transformDate(),
                 fontSize = 14.sp)
        }

        LocalAlarmImageLoader.current(
            AlarmImageLoadData(model                = alarmListItem.pictureUrl,
                                    contentDescription   = null,
                                    contentScale         = ContentScale.Crop,
                                    modifier             = Modifier.layoutId("reviewImage")
                                                                  .testTag("reviewImage")
                                                                  .size(45.dp)
                                                                  .clickable { onContents.invoke() }
                                                                  .clip(RoundedCornerShape(8.dp))
                                                                  .background(Color(0x11000000)))
        )
    }
}

fun alarmListItemConstraintSet(): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val reviewImage = createRefFor("reviewImage")
        val content = createRefFor("content")

        constrain(image) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }

        constrain(reviewImage) {
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }

        constrain(content) {
            start.linkTo(image.end, 10.dp)
            end.linkTo(reviewImage.start, 10.dp)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
        }
    }
}

@Preview
@Composable
fun PreviewAlarmItem() {
    AlarmItem(alarmListItem = testAlarmListItem(), onContents = {}, onProfile = {})

}