package dybarsky.agecounter

import android.app.Application

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    private val persistence by lazy { Persistence() }
    private val scheduler by lazy { Scheduler(this) }
    private val remote by lazy { Remote(this, persistence) }
    private val worker by lazy { Worker(remote) }

    fun startWidget(birthday: Long, widgetId: Int) {
        persistence.birthday = birthday
        remote.update(widgetId)
        worker.start(widgetId)
        scheduler.schedule(widgetId, birthday)
    }

    fun stopWidget(widgetId: Int) {
        worker.stop(widgetId)
        scheduler.cancel()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
