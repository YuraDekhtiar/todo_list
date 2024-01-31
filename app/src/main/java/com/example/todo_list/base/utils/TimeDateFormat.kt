package com.example.todo_list.base.utils

import java.text.SimpleDateFormat
import java.util.Locale

object TimeDateFormat {
    private const val TIME_FORMAT = "HH:mm"
    private const val DATE_FORMAT = "dd/MM/yyyy"
    private val LOCALE = Locale.ENGLISH

    private val simpleTimeFormat = SimpleDateFormat(TIME_FORMAT, LOCALE)
    private val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, LOCALE)

    fun timeStampToFormatTime(timestamp: Long): String {
        return simpleTimeFormat.format(timestamp)
    }

    fun timeStampToFormatDate(timestamp: Long): String {
        return simpleDateFormat.format(timestamp)
    }

    fun formatDateTimeToTimeStamp(time: String, date: String): Long {
        return SimpleDateFormat("$TIME_FORMAT $DATE_FORMAT", LOCALE)
            .parse("$time $date")?.time ?: 0
    }
}

