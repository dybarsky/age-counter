package dybarsky.agecounter

import java.util.Calendar

class ConfigPresenter(private val view: View) {

    private val model = Model()

    fun save() {
        val birthday = model.calendar.timeInMillis
        val widgetId = model.widgetId
        App.instance.startWidget(birthday, widgetId)
        view.close()
    }

    fun setWidgetId(widgetId: Int) {
        model.widgetId = widgetId
    }

    fun setDay(day: Int) {
        model.day = day
        view.displayDay(day.toString())
        check()
    }

    fun setMonth(month: String) {
        model.month = month
        view.displayMonth(month)
        check()
    }

    fun setYear(year: Int) {
        model.year = year
        view.displayYear(year.toString())
        check()
    }

    private fun check() {
        view.setReady(model.isValid)
    }
}


private class Model(
    var day: Int = -1,
    var month: String? = null,
    var year: Int = -1,
    var widgetId: Int = -1
) {

    val isValid: Boolean
        get() = day != -1 && month != null && year != -1

    val calendar: Calendar
        get() = Calendar.getInstance().apply {
            set(year,
                month?.let { months.indexOf(it) } ?: 0,
                day,
                0, 0, 0)
        }
}

interface View {
    fun displayDay(day: String)
    fun displayMonth(month: String)
    fun displayYear(year: String)
    fun setReady(ready: Boolean)
    fun close()
}

