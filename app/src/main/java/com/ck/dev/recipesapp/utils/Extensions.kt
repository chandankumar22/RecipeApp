package com.ck.dev.recipesapp.utils

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

const val TAG = "Extensions"

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, { it?.let { t -> action(t) } })
}

fun View.setRecipeImage(url:String){
    Log.i(TAG,"setRecipeImage called")
    val req = RequestOptions
        .fitCenterTransform()
        .transform(
            RoundedCorners(5)
        )
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .override(width, height)

    Glide.with(context).load(url).apply(req)
        .into(object : SimpleTarget<Drawable>() {
            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable>?
            ) {
                background = resource
            }
        })
}