package com.sryang.torang.util

import android.util.Log
import com.sryang.torang.data1.alarm.AlarmListItem
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors
import kotlin.math.abs

fun convertDate(list: List<AlarmListItem>): List<AlarmListItem> {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val now = Date(System.currentTimeMillis())
    val today: List<AlarmListItem> = list.stream().filter { data ->
        try {
            val d = sdf.parse(data.createdDate)
            val diffHour: Long = TimeUnit.HOURS.convert(
                abs(d.time - now.time),
                TimeUnit.MILLISECONDS
            )
            return@filter diffHour < 24
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        false
    }.collect(Collectors.toList())
    val thisWeek: List<AlarmListItem> = list.stream().filter { data ->
        try {
            val d: Date = sdf.parse(data.createdDate) as Date
            val diffHour: Long = TimeUnit.HOURS.convert(
                abs(d.time - now.time),
                TimeUnit.MILLISECONDS
            )
            return@filter diffHour > 24 && diffHour < 24 * 7
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        false
    }.collect(Collectors.toList())
    val thisMonth: List<AlarmListItem> = list.stream().filter { data ->
        try {
            val d: Date = sdf.parse(data.createdDate) as Date
            val diffHour: Long = TimeUnit.HOURS.convert(
                abs(d.time - now.time),
                TimeUnit.MILLISECONDS
            )
            return@filter diffHour > 24 * 7 && diffHour < 24 * 30
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        false
    }.collect(Collectors.toList())
    val other: List<AlarmListItem> = list.stream().filter { data ->
        try {
            val d: Date = sdf.parse(data.createdDate) as Date
            val diffHour: Long = TimeUnit.HOURS.convert(
                abs(d.time - now.time),
                TimeUnit.MILLISECONDS
            )
            Log.d("__convertDate", "diffHour : $diffHour")
            return@filter diffHour > 24 * 30
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        false
    }.collect(Collectors.toList())
    val list1 = ArrayList<AlarmListItem>()
    if (today.isNotEmpty()) {
        list1.add(AlarmListItem(indexDate = "오늘"))
        list1.addAll(today)
    }
    if (thisWeek.isNotEmpty()) {
        list1.add(AlarmListItem(indexDate = "이번주"))
        list1.addAll(thisWeek)
    }
    if (thisMonth.isNotEmpty()) {
        list1.add(AlarmListItem(indexDate = "이번달"))
        list1.addAll(thisMonth)
    }
    if (other.isNotEmpty()) {
        list1.add(AlarmListItem(indexDate = "오래전"))
        list1.addAll(other)
    }
    return list1
}