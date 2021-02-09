package dybarsky.agecounter

import android.util.Log
import android.util.SparseArray
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Worker(private val remote: Remote) {

    companion object {
        private const val DELAY = 3156L
    }

    private val scope = MainScope()
    private val jobs = SparseArray<Job>()

    fun start(widgetId: Int) {
        Log.d("###", "start $widgetId")

        val job = scope.launch {
            while (true) {
                Log.d("###", "refresh $widgetId")
                remote.update(widgetId)
                delay(DELAY)
            }
        }
        jobs.put(widgetId, job)
    }

    fun stop(widgetId: Int) {
        Log.d("###", "stop $widgetId")
        jobs[widgetId]?.cancel()
        jobs.delete(widgetId)
    }
}
