package com.toobeeinfo.qrcodescanner.ui.utils

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.toFormattedDisplay():String{
    val simpleDateFormat =  SimpleDateFormat("dd-MM-yyyy hh:mm a",Locale.US)
    return simpleDateFormat.format(this.time)
}