package com.effectivemobilett.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import jp.wasabeef.glide.transformations.BitmapTransformation
import java.security.MessageDigest

class CutOfLogo(private val cropHeight: Int) : BitmapTransformation() {

    override fun transform(
        context: Context,
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap = Bitmap.createBitmap(
        toTransform,
        0,
        0,
        outWidth,
        outHeight + cropHeight
    )

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {}

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CutOfLogo

        return cropHeight == other.cropHeight
    }

    override fun hashCode(): Int {
        return cropHeight
    }
}
