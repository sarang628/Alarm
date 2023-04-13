package com.sarang.screen_alarm2

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

internal class AlarmRvAdt :
    RecyclerView.Adapter<AlarmVH>() {
    private var alarms: List<AlarmListItem> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmVH {
        return AlarmVH.create(parent)
    }

    override fun onBindViewHolder(holder: AlarmVH, position: Int) {
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