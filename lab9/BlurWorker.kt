package com.example.bluromatic.workers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import java.io.FileOutputStream

class BlurWorker(context: Context, params: WorkerParameters) :
    Worker(context, params) {

    override fun doWork(): Result {
        return try {
            val resources = applicationContext.resources

            val bitmap = BitmapFactory.decodeResource(
                resources,
                com.example.bluromatic.R.drawable.android_cupcake
            )

            val output = blurBitmap(bitmap)

            val outputFile = File(
                applicationContext.cacheDir,
                "blurred.png"
            )

            val out = FileOutputStream(outputFile)
            output.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.flush()
            out.close()

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    private fun blurBitmap(bitmap: Bitmap): Bitmap {
        val width = bitmap.width
        val height = bitmap.height

        val blurred = Bitmap.createBitmap(width, height, bitmap.config)

        for (x in 1 until width - 1) {
            for (y in 1 until height - 1) {

                val pixel = bitmap.getPixel(x, y)

                val r = (pixel shr 16 and 0xff)
                val g = (pixel shr 8 and 0xff)
                val b = (pixel and 0xff)

                val nr = (r * 0.7).toInt()
                val ng = (g * 0.7).toInt()
                val nb = (b * 0.7).toInt()

                val newPixel =
                    (0xff shl 24) or
                            (nr shl 16) or
                            (ng shl 8) or
                            nb
}