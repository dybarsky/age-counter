package dybarsky.agecounter

import android.app.Application

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    private val persistence by lazy { Persistence() }
    private val scheduler by lazy { Scheduler(this) }
    private val remote by lazy { Remote(this) }

    fun startWidget(birthday: Long, widgetId: Int) {
        persistence.birthday = birthday
        scheduler.schedule(widgetId, birthday)
    }

    fun stopWidget(widgetId: Int) {
        scheduler.cancel(widgetId)
    }

    fun refreshWidget(widgetId: Int) {
        val birthday = persistence.birthday
        val (whole, fraction) = getAge(birthday).split()
        remote.update(widgetId, whole, fraction)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
