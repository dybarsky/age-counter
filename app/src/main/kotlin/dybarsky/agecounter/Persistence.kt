package dybarsky.agecounter

import android.content.Context
import androidx.core.content.edit

class Persistence(context: Context) {

    companion object {
        private const val NAME = "AGE"
        private const val KEY = "age"
    }

    private val prefs by lazy {
        context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    var birthday: Long
        get() = prefs.getLong(KEY, -1)
        set(value) = prefs.edit { putLong(KEY, value) }
}
