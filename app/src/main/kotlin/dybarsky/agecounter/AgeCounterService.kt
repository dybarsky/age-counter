package dybarsky.agecounter

import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AgeCounterService : Service() {

    companion object {

        private const val ARG_ID = "widget id"
        private const val ARG_BIRTHDAY = "birthday millis"

        private var Intent.widgetId: Int
            get() = getIntExtra(ARG_ID, -1)
            set(value) { putExtra(ARG_ID, value) }

        private var Intent.birthday: Long
            get() = getLongExtra(ARG_BIRTHDAY, -1)
            set(value) { putExtra(ARG_BIRTHDAY, value) }

        fun intent(context: Context) = Intent(context, AgeCounterService::class.java)

        fun intent(context: Context, widgetId: Int, age: Long) =
            intent(context).also {
                it.birthday = age
                it.widgetId = widgetId
            }
    }

    private val scope = MainScope()

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val birthday = intent.birthday
        val widgetId = intent.widgetId

        val remote = createRemote(this)
        val widgetManager = AppWidgetManager.getInstance(this)

        fun update() {
            val (whole, fraction) = getAge(birthday).split()
            remote.update(whole, fraction)
            widgetManager.updateAppWidget(widgetId, remote)
        }

        scope.launch {
            while (true) {
                update()
                delay(1000)
            }
        }
        return START_STICKY_COMPATIBILITY
    }
}
