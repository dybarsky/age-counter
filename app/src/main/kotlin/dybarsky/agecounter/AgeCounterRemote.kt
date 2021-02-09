package dybarsky.agecounter

import android.content.Context
import android.widget.RemoteViews

fun createRemote(context: Context) = RemoteViews(context.packageName, R.layout.widget_layout)

fun RemoteViews.update(ageWhole: Int, ageFraction: Int) {
    setTextViewText(R.id.years_whole, ageWhole.toString())
    setTextViewText(R.id.years_fraction, ".$ageFraction")
}
