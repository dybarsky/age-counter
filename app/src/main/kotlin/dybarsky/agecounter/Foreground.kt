package dybarsky.agecounter

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_MIN
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.ForegroundInfo

private const val CHANNEL_ID = "channel"
private const val CHANNEL_NAME = "Age counter notification channel"
private const val NOTIFICATION_ID = 100

fun Context.createForegroundInfo(): ForegroundInfo {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        createChannel()
    }
    val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_stat_name)
        .build()

    return ForegroundInfo(NOTIFICATION_ID, notification)
}

@RequiresApi(Build.VERSION_CODES.O)
private fun Context.createChannel() {
    val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, IMPORTANCE_MIN)
    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)
}
