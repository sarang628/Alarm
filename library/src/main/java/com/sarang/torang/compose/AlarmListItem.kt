package com.sarang.torang.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.sarang.torang.data1.alarm.AlarmListItem
import com.sarang.torang.uistate.testAlarmListItem


@Composable
fun AlarmListItem(alarmListItem: AlarmListItem, onContents: () -> Unit, onProfile: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onProfile.invoke() }
            .padding(start = 8.dp, end = 8.dp)
            .height(65.dp),
        constraintSet = alarmListItemConstraintSet()
    ) {
        AsyncImage(
            model = alarmListItem.otherPictureUrl,
            contentDescription = "",
            Modifier
                .layoutId("image")
                .size(50.dp)
                .clip(CircleShape)
                .background(Color(0x11000000)),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .layoutId("contents")
                .width(0.dp)
                .height(50.dp)
                .clickable {
                    onContents.invoke()
                },
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = alarmListItem.contents)
            Text(text = alarmListItem.transformDate())
        }

        AsyncImage(
            model = alarmListItem.pictureUrl,
            contentDescription = "",
            Modifier
                .layoutId("reviewImage")
                .size(50.dp)
                .clickable {
                    onContents.invoke()
                }
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0x11000000)),
            contentScale = ContentScale.Crop
        )
    }
}

fun alarmListItemConstraintSet(): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val reviewImage = createRefFor("reviewImage")
        val contents = createRefFor("contents")

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

        constrain(contents) {
            start.linkTo(image.end, 8.dp)
            end.linkTo(reviewImage.start, 8.dp)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
        }
    }
}

@Preview
@Composable
fun PreviewAlarmListItem() {
    AlarmListItem(alarmListItem = testAlarmListItem(), onContents = {}, onProfile = {})

}