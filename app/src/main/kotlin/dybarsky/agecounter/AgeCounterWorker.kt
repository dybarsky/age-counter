package dybarsky.agecounter

import android.appwidget.AppWidgetManager
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AgeCounterWorker {

    companion object {
        private const val DELAY = 200L
    }

    private var scope: CoroutineScope? = null

    fun start(context: Context, widgetId: Int, birthday: Long) {
        val remote = createRemote(context)
        val widgetManager = AppWidgetManager.getInstance(context)

        fun update() {
            val (whole, fraction) = getAge(birthday).split()
            remote.update(whole, fraction)
            widgetManager.updateAppWidget(widgetId, remote)
        }

        scope = MainScope()
        scope?.launch {
            while (true) {
                update()
                delay(DELAY)
            }
        }
    }

    fun stop() {
        scope?.cancel()
    }
}
