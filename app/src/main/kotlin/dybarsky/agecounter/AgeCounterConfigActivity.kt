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

    private val model = Model(null, null, null)

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

        binding.setup()
    }

    private fun ActivityConfigBinding.setup() {
        day.setOnClickListener {
            showDialog(days) {
                day.text = it.toString()
                model.day = it
                check()
            }
        }
        month.setOnClickListener {
            showDialog(months) {
                month.text = it
                model.month = it
                check()
            }
        }
        year.setOnClickListener {
            showDialog(years) {
                year.text = it.toString()
                model.year = it
                check()
            }
        }
        done.setOnClickListener {
            startCounter()
            close()
        }
    }

    private fun check() {
        binding.done.isEnabled = model.isValid
    }

    private fun startCounter() {
        val calendar = Calendar.getInstance().apply {
            set(model.year ?: 0,
                months.indexOf(model.month ?: 0),
                model.day ?: 0,
                0, 0, 0)
        }
        AgeCounterApp.instance.worker.start(this, widgetId, calendar.timeInMillis)
    }

    private fun close() {
        val resultValue = Intent().apply {
            putExtra(EXTRA_APPWIDGET_ID, widgetId)
        }
        setResult(Activity.RESULT_OK, resultValue)
        finish()
    }

}

class Model(
    var day: Int?,
    var month: String?,
    var year: Int?
) {

    val isValid: Boolean
        get() = day != null && month != null && year != null

}
