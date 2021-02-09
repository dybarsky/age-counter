package dybarsky.agecounter

import android.app.Activity
import android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_ID
import android.appwidget.AppWidgetManager.INVALID_APPWIDGET_ID
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import dybarsky.agecounter.databinding.ActivityConfigBinding

class ConfigActivity : AppCompatActivity(), View {

    private var widgetId: Int = -1

    private val binding by lazy {
        ActivityConfigBinding.inflate(layoutInflater)
    }

    private val presenter by lazy {
        ConfigPresenter(this)
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

        presenter.setWidgetId(widgetId)
        binding.setup()
    }

    private fun ActivityConfigBinding.setup() {
        day.onClick {
            showDialog(days) {
                presenter.setDay(it)
            }
        }
        month.onClick {
            showDialog(months) {
                presenter.setMonth(it)
            }
        }
        year.onClick {
            showDialog(years) {
                presenter.setYear(it)
            }
        }
        done.onClick {
            presenter.save()
        }
    }

    override fun close() {
        val resultValue = Intent().apply {
            putExtra(EXTRA_APPWIDGET_ID, widgetId)
        }
        setResult(Activity.RESULT_OK, resultValue)
        finish()
    }

    override fun displayDay(day: String) {
        binding.day.text = day
    }

    override fun displayMonth(month: String) {
        binding.month.text = month
    }

    override fun displayYear(year: String) {
        binding.year.text = year
    }

    override fun setReady(ready: Boolean) {
        binding.done.isEnabled = ready
    }
}


