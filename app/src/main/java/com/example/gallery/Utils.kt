package com.example.gallery

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import java.io.ByteArrayOutputStream

class Utils {

    companion object {
        fun imageViewToBytes(iv: ImageView): ByteArray {
            val bitmap = (iv.getDrawable() as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)
            return stream.toByteArray()
        }

        fun bytesToBitmap(picture: ByteArray): Bitmap {
            return BitmapFactory.decodeByteArray(picture, 0, picture.size)
        }
    }

}
