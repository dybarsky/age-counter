package dybarsky.agecounter

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Worker(
    private val context: Context,
    workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {

    companion object {
        private const val DELAY = 3156L
        private const val ARG_ID = "widget id"

        private val job = Job()
        private val scope = CoroutineScope(job + Dispatchers.Main)

        fun createData(widgetId: Int) = Data.Builder().putInt(ARG_ID, widgetId).build()
    }

    override suspend fun doWork(): Result {
        setForeground(context.createForegroundInfo())
        job.cancelChildren()
        scope.launch {
            while (true) {
                val widgetId = inputData.getInt(ARG_ID, -1)
                Log.d("###", "refresh $widgetId")
                App.instance.refreshWidget(widgetId)
                delay(DELAY)
            }
        }
        return Result.success()
    }
}
