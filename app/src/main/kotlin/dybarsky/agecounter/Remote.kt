package dybarsky.agecounter

import android.appwidget.AppWidgetManager
import android.content.Context
import android.widget.RemoteViews

class Remote(context: Context, private val persistence: Persistence) {

    private val drawer = Drawer(context)
    private val views = RemoteViews(context.packageName, R.layout.widget_layout)
    private val widgetManager = AppWidgetManager.getInstance(context)

    fun update(widgetId: Int) {
        val birthday = persistence.birthday
        val (whole, fraction) = getAge(birthday).split()
        views.update(whole, fraction)
        widgetManager.updateAppWidget(widgetId, views)
    }

    private fun RemoteViews.update(ageWhole: Int, ageFraction: Int) {
        setImageViewBitmap(R.id.image, drawer.draw(ageWhole, ageFraction))
    }
}
