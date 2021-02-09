package dybarsky.agecounter

import android.app.AlarmManager
import android.content.Context
import android.util.Log
import androidx.core.app.AlarmManagerCompat

class Scheduler(private val context: Context) {

    companion object {
        private const val INTERVAL = 15 * 60 * 1000L
    }

    private val alarmManager by lazy {
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    fun schedule(widgetId: Int, age: Long) {
        Log.d("###", "schedule $widgetId $age")
        AlarmManagerCompat.setExact(
            alarmManager,
            AlarmManager.RTC,
            System.currentTimeMillis() + INTERVAL,
            Receiver.intent(context, widgetId, age)
        )
    }

    fun cancel() {
        Log.d("###", "cancel")
        alarmManager.cancel(Receiver.intent(context))
    }

}
