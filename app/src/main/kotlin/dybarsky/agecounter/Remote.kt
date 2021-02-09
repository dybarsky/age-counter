package dybarsky.agecounter

import android.appwidget.AppWidgetManager
import android.content.Context
import android.widget.RemoteViews

class Remote(context: Context, private val persistence: Persistence) {

    private val remote = RemoteViews(context.packageName, R.layout.widget_layout)
    private val widgetManager = AppWidgetManager.getInstance(context)

    fun update(widgetId: Int) {
        val birthday = persistence.birthday
        val (whole, fraction) = getAge(birthday).split()
        remote.update(whole, fraction)
        widgetManager.updateAppWidget(widgetId, remote)
    }

    private fun RemoteViews.update(ageWhole: Int, ageFraction: Int) {
        setTextViewText(R.id.years_whole, ageWhole.toString())
        setTextViewText(R.id.years_fraction, ".$ageFraction")
    }
}


