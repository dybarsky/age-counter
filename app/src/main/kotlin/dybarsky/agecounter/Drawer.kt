package dybarsky.agecounter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface

class Drawer(context: Context) {

    companion object {
        private const val OFFSET_X = 100f
        private const val OFFSET_Y = 140f
        private const val FONT_SIZE = 100f
        private const val BITMAP_WIDTH = 600
        private const val BITMAP_HEIGHT = 200
    }
    private val font = Typeface.createFromAsset(context.assets, "font/oswald_regular.ttf");
    private val bitmap = Bitmap.createBitmap(BITMAP_WIDTH, BITMAP_HEIGHT, Bitmap.Config.ARGB_4444)
    private val canvas = Canvas(bitmap)
    private val paint = Paint().apply {
        isAntiAlias = true
        typeface = font
        textSize = FONT_SIZE
        style = Paint.Style.FILL
        color = Color.WHITE
    }

    fun draw(ageWhole: Int, ageFraction: Int): Bitmap {
        bitmap.eraseColor(Color.TRANSPARENT)
        canvas.drawText("$ageWhole.$ageFraction", OFFSET_X, OFFSET_Y, paint)
        return bitmap
    }
}
