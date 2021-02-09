package dybarsky.agecounter

import android.app.Activity
import android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_ID
import android.appwidget.AppWidgetManager.INVALID_APPWIDGET_ID
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dybarsky.agecounter.databinding.ActivityConfigBinding
import java.util.Calendar

class AgeCounterConfigActivity : AppCompatActivity() {

    private var widgetId: Int = -1
    private val binding by lazy {
        ActivityConfigBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        widgetId = intent
            ?.extras
            ?.getInt(EXTRA_APPWIDGET_ID, INVALID_APPWIDGET_ID)
            ?: INVALID_APPWIDGET_ID

        with(binding) {
            day.setOnClickListener {

            }
            month.setOnClickListener {

            }
            year.setOnClickListener {

            }
            done.setOnClickListener {
                startCounter()
                close()
            }
        }
    }

    private fun getBirthday() = Calendar.getInstance().apply { set(1987, 7, 9, 0, 0, 0) } // todo

    private fun startCounter() {
        val intent = AgeCounterService.intent(this, widgetId, getBirthday().timeInMillis)
        startService(intent)
    }

    private fun close() {
        val resultValue = Intent().apply {
            putExtra(EXTRA_APPWIDGET_ID, widgetId)
        }
        setResult(Activity.RESULT_OK, resultValue)
        finish()
    }

}
