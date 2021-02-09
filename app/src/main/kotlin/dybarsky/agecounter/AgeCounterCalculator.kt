package dybarsky.agecounter

import kotlin.math.pow


private const val FRACTION_DIGITS = 8
private const val MILLIS_IN_YEAR = 31556952000.0

private val fractionMultiplier = 10.0.pow(FRACTION_DIGITS)

fun Double.split(): Pair<Int, Int> {
    val whole = (this).toInt()
    val fraction = ((this - whole) * fractionMultiplier).toInt()
    return whole to fraction
}

fun getAge(birthday: Long): Double {
    val now = System.currentTimeMillis()
    val diff = now - birthday
    return diff / MILLIS_IN_YEAR
}

