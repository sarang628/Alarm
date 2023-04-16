package com.sarang.alarm.recyclerview

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarang.alarm.fragment.AlarmListItem

internal class AlarmAdapter :
    RecyclerView.Adapter<AlarmViewHolder>() {
    private var alarms: List<AlarmListItem> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.setAlarm(alarms[position])
    }

    override fun getItemCount(): Int {
        return alarms.size
    }

    fun setAlarm(list: List<AlarmListItem>) {
        Log.i("AlarmRvAdt", "setAlarm : " + list.size)
        this.alarms = list
        notifyDataSetChanged()
    }
}