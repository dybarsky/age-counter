package dybarsky.agecounter

const val SHORT_DELAY = 150L
const val LONG_DELAY = 300L

fun android.view.View.onClick(delay: Long = SHORT_DELAY, callback: () -> Unit) {
    setOnClickListener {
        postDelayed(callback, delay)
    }
}
