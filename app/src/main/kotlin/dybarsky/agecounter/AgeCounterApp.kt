package dybarsky.agecounter

import android.app.Application

class AgeCounterApp : Application() {

    companion object {
        lateinit var instance: AgeCounterApp
    }

    val worker by lazy { AgeCounterWorker() }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
