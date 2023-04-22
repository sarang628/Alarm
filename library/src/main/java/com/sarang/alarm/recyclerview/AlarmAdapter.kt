package com.sarang.alarm.recyclerview

import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sarang.alarm.uistate.AlarmListItem

internal class AlarmAdapter constructor(
    val clickUser: ClickableSpan,
    val clickPost: ClickableSpan,
    val clickImage: OnClickListener? = null
) :
    RecyclerView.Adapter<ViewHolder>() {
    private var alarms: List<AlarmListItem> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == 0) {
            return AlarmIndexViewHolder.create(parent)
        } else {
            return AlarmViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is AlarmViewHolder) {
            holder.setAlarm(alarms[position])
            holder.itemAlarmBinding.tvContents.text =
                alarms[position].toTextViewMessage(clickUser = clickUser, clickPost = clickPost)
            holder.itemAlarmBinding.tvContents.movementMethod = LinkMovementMethod.getInstance()

            holder.itemAlarmBinding.ivProfile.setOnClickListener(clickImage)

        } else if (holder is AlarmIndexViewHolder) {
            holder.setAlarm(alarms[position].indexDate)
        }
    }

    override fun getItemCount(): Int {
        return alarms.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (alarms[position].indexDate.isNotEmpty()) 0 else 1
    }

    fun setAlarm(list: List<AlarmListItem>) {
        Log.i("AlarmRvAdt", "setAlarm : " + list.size)
        this.alarms = list
        notifyDataSetChanged()
    }
}