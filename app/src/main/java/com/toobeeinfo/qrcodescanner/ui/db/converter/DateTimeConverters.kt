package com.toobeeinfo.qrcodescanner.ui.db.converter

import androidx.room.TypeConverter
import java.util.*


class DateTimeConverters {
    @TypeConverter
    fun toCalendar(l:Long) : Calendar?{
        var c = Calendar.getInstance()
        c!!.timeInMillis = l
        return c
    }
    @TypeConverter
    fun fromCalendar(c:Calendar?) :Long?{
        return c?.time?.time
    }
}