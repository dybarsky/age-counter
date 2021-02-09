package dybarsky.agecounter

import android.appwidget.AppWidgetProvider
import android.content.Context

class AgeCounterWidgetProvider : AppWidgetProvider() {

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        super.onDeleted(context, appWidgetIds)
        context.stopService(AgeCounterService.intent(context))
    }
}
