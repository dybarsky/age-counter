package dybarsky.agecounter

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log

class WidgetProvider : AppWidgetProvider() {

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        super.onDeleted(context, appWidgetIds)
        App.instance.stopWidget(appWidgetIds[0])
        Log.d("###", "deleted $appWidgetIds")
    }

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        Log.d("###", "enabled")
    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
        Log.d("###", "disabled")
    }

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Log.d("###", "update")

    }
}
