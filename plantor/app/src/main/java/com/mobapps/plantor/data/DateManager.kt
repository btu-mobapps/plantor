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

    public fun compareDates (context: Context) {
        val df: DateFormat = SimpleDateFormat("dd/M/yyyy h:mm")
        val date: String = df.format(Calendar.getInstance().time)

        Toast.makeText(context, checktimings("01/2/2021 3:42", date).toString(), Toast.LENGTH_LONG).show()
    }

    private fun checktimings(time: String, endtime: String): Boolean {
        val pattern = "dd/M/yyyy h:mm"
        val sdf = SimpleDateFormat(pattern)
        try {
            val date1 = sdf.parse(time)
            val date2 = sdf.parse(endtime)

            return date1.before(date2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return false
    }

}