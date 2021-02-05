package com.mobapps.plantor.data

import android.content.Context
import android.widget.Toast
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

public class DateManager {

    companion object {
        private var INSTANCE: DateManager? = null

        public fun getInstance(): DateManager? {
            if (INSTANCE == null) {
                INSTANCE = DateManager()
            }

            return INSTANCE
        }
    }

    val dFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    fun compareToday (context: Context, date: String) {
        val today: String = getCurrentDate()

        Toast.makeText(context, checkBefore(date, today).toString(), Toast.LENGTH_LONG).show()
    }

    fun getCurrentDate (): String {
        return dFormat.format(Calendar.getInstance().time)
    }

    fun checkBefore(beforeTime: String, afterTime: String): Boolean {
        try {
            val date1 = dFormat.parse(beforeTime)
            val date2 = dFormat.parse(afterTime)

            return date1.before(date2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return false
    }

}