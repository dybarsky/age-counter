package dybarsky.agecounter

import android.appwidget.AppWidgetManager
import android.content.Context
import android.util.Log
import android.widget.RemoteViews

class Remote(context: Context) {

    private val drawer = Drawer(context)
    private val views = RemoteViews(context.packageName, R.layout.widget_layout)
    private val widgetManager = AppWidgetManager.getInstance(context)

    fun update(widgetId: Int, ageWhole: Int, ageFraction: Int) {
        Log.d("###", "$ageWhole.$ageFraction")
        val image = drawer.draw(ageWhole, ageFraction)
        views.setImageViewBitmap(R.id.image, image)
        widgetManager.updateAppWidget(widgetId, views)
    }
}
