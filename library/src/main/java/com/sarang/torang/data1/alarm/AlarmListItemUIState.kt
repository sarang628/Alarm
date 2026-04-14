package com.sarang.torang.data1.alarm

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

/** 알림 리스트 데이터 */
sealed interface AlarmListItemUIState {
    class Header(val title: String = "") : AlarmListItemUIState
    class Item(val id               : Int           = 0,
               val user             : AlarmUser?    = null,
               val contents         : String        = "",
               val otherPictureUrl  : String        = "",
               val createdDate      : String        = "",
               val pictureUrl       : String        = "",
               val reviewId         : Int           = 0,
               val otherUserId      : Int           = 0,
               val type             : AlarmType?    = null) : AlarmListItemUIState
    class InitLoadingFailed()
}

    fun AlarmListItemUIState.Item.toTextViewMessage(onUserClick: () -> Unit = {},
                                                    onPostClick: () -> Unit = {}): AnnotatedString {
        if (user == null)
            return buildAnnotatedString {  }

        return when (type) {
            AlarmType.LIKE -> getLikeMessage(
                userName = user.name,
                onUserClick = onUserClick,
                onPostClick = onPostClick
            )

            AlarmType.REPLY -> getReplyMessage(
                userName = user.name,
                onUserClick = onUserClick,
                onPostClick = onPostClick
            )

            AlarmType.FOLLOW -> getFollowMessage(
                userName = user.name,
                onUserClick = onUserClick
            )
            else -> buildAnnotatedString {  }
        }
    }

    fun getLikeMessage(
        userName: String,
        onUserClick: () -> Unit = {},
        onPostClick: () -> Unit = {}
    ): AnnotatedString {
        return buildAnnotatedString {
            // 1. 유저 이름 영역 (스타일 및 링크 추가)
            pushStringAnnotation(tag = "USER", annotation = userName)
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Blue)) {
                append(userName)
            }
            pop()

            append("님이 ")

            // 2. 포스트 영역 (스타일 및 링크 추가)
            val postText = "이 포스트"
            pushStringAnnotation(tag = "POST", annotation = "post_id_or_data")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Blue)) {
                append(postText)
            }
            pop()

            append("를 좋아합니다.")
        }
    }

fun getReplyMessage(
    userName: String,
    onUserClick: () -> Unit = {},
    onPostClick: () -> Unit = {}
): AnnotatedString {
    return buildAnnotatedString {
        // 유저 이름 부분
        withLink(LinkAnnotation.Clickable(tag = "user", linkInteractionListener = { onUserClick() })) {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(userName)
            }
        }

        append("님이 ")

        // 포스트 부분
        withLink(LinkAnnotation.Clickable(tag = "post", linkInteractionListener = { onPostClick() })) {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("이 포스트")
            }
        }

        append("에 댓글을 달았습니다.")
    }
}

fun getFollowMessage(
    userName: String,
    onUserClick: () -> Unit = {}
): AnnotatedString {
    return buildAnnotatedString {
        // 유저 이름 부분
        withLink(LinkAnnotation.Clickable(tag = "user", linkInteractionListener = { onUserClick() })) {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(userName)
            }
        }

        append("님이 팔로우 하였습니다.")
    }
}

    fun AlarmListItemUIState.Item.transformDate(): String {
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val diff = Date(System.currentTimeMillis()).time - sdf.parse(createdDate).time
            val sb = StringBuilder()
            if (diff < TimeUnit.MINUTES.toMillis(1)) {
                return TimeUnit.MILLISECONDS.toSeconds(diff).toString() + "초 전"
            } else if (diff > TimeUnit.MINUTES.toMillis(1)
                && diff < TimeUnit.HOURS.toMillis(1)
            ) {
                return TimeUnit.MILLISECONDS.toMinutes(diff).toString() + "분 전"
            } else if (diff > TimeUnit.HOURS.toMillis(1)
                && diff < TimeUnit.DAYS.toMillis(1)
            ) {
                return TimeUnit.MILLISECONDS.toHours(diff).toString() + "시간 전"
            } else if (diff > TimeUnit.DAYS.toMillis(1)
                && diff < TimeUnit.DAYS.toMillis(7)
            ) {
                return TimeUnit.MILLISECONDS.toDays(diff).toString() + "일 전"
            }
            return (TimeUnit.MILLISECONDS.toDays(diff) / 7).toString() + "주 전"
        } catch (e: Exception) {

        }
        return ""
    }