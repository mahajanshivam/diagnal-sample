package com.shivam.diagnalsample.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.shivam.diagnalsample.R

class PosterImageMappingUtil {
    companion object {

        fun getPosterDrawable(context: Context, posterImageName: String?): Drawable? {

            return when (posterImageName) {
                "poster1.jpg" -> ContextCompat.getDrawable(context, R.drawable.poster1)
                "poster2.jpg" -> ContextCompat.getDrawable(context, R.drawable.poster2)
                "poster3.jpg" -> ContextCompat.getDrawable(context, R.drawable.poster3)
                "poster4.jpg" -> ContextCompat.getDrawable(context, R.drawable.poster4)
                "poster5.jpg" -> ContextCompat.getDrawable(context, R.drawable.poster5)
                "poster6.jpg" -> ContextCompat.getDrawable(context, R.drawable.poster6)
                "poster7.jpg" -> ContextCompat.getDrawable(context, R.drawable.poster7)
                "poster8.jpg" -> ContextCompat.getDrawable(context, R.drawable.poster8)
                "poster9.jpg" -> ContextCompat.getDrawable(context, R.drawable.poster9)
                else -> ContextCompat.getDrawable(
                    context,
                    R.drawable.placeholder_for_missing_posters
                )

            }
        }
    }
}