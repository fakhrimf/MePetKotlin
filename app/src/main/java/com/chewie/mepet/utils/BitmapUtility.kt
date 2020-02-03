package com.chewie.mepet.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream


object BitmapUtility {
    fun getEncodedImage(bitmap: Bitmap): String {
        val stream = ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream)
        val byteArray: ByteArray = stream.toByteArray()
        stream.close()
        val encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT)
        return encodedImage
    }

    fun getDecodedImage(encodedImage: String): Bitmap {
        val decodedString = Base64.decode(encodedImage, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    fun getResizedImage(bm: Bitmap, maxSize: Int): Bitmap {
        var width: Int = bm.width
        var height: Int = bm.height

        val bitmapRatio = (width / height).toFloat()
        if (bitmapRatio > 1) {
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height * bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(bm, width, height, true)
    }
}
