package dybarsky.agecounter

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ReceiverLegacy : BroadcastReceiver() {

    companion object {

        private const val ARG_ID = "widget id"
        private const val ARG_BIRTHDAY = "birthday millis"

        private var Intent.widgetId: Int
            get() = getIntExtra(ARG_ID, -1)
            set(value) { putExtra(ARG_ID, value) }

        private var Intent.birthday: Long
            get() = getLongExtra(ARG_BIRTHDAY, -1)
            set(value) { putExtra(ARG_BIRTHDAY, value) }

        fun intent(context: Context): PendingIntent {
            val intent = Intent(context, ReceiverLegacy::class.java)
            return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        fun intent(context: Context, widgetId: Int, age: Long): PendingIntent {
            val intent = Intent(context, ReceiverLegacy::class.java).also {
                it.birthday = age
                it.widgetId = widgetId
            }
            return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        val birthday = intent.birthday
        val widgetId = intent.widgetId

        Log.d("###", "receive $widgetId $birthday")

        App.instance.stopWidget(widgetId)
        App.instance.startWidget(birthday, widgetId)
    }

}
