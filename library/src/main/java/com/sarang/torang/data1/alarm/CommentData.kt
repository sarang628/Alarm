package com.sarang.torang.data1.alarm

data class CommentData(
    val userId: Int,
    val profileImageUrl: String,
    val date: String,
    val comment: String,
    val name: String,
    val likeCount: Int
)

data class CommentDataUiState(
    val commentList: List<CommentData>,
    val myProfileUrl: String
)