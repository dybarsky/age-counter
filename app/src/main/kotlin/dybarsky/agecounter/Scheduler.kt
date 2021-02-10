package dybarsky.agecounter

import android.content.Context
import android.util.Log
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import dybarsky.agecounter.Worker.Companion.createData
import java.util.concurrent.TimeUnit

class Scheduler(context: Context) {

    companion object {
        private const val INTERVAL = 15L
    }

    private val workManager = WorkManager.getInstance(context)

    fun schedule(widgetId: Int, age: Long) {
        Log.d("###", "schedule $widgetId $age")

        val request = PeriodicWorkRequest
            .Builder(Worker::class.java, INTERVAL, TimeUnit.MINUTES)
            .setInputData(createData(widgetId))
            .addTag(widgetId.toString())
            .build()

        workManager.enqueue(request)
    }

    fun cancel(widgetId: Int) {
        Log.d("###", "cancel")
        workManager.cancelAllWorkByTag(widgetId.toString())
    }

}
