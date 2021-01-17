package id.pradiptapaa.github.infrastructure.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.loadFromUrl(url: String) =
        Glide.with(this.context.applicationContext)
                .load(url)
                .override(300,300)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)


fun ImageView.loadFromDrawable(drawable: Int) =
        Glide.with(this.context.applicationContext)
                .load(drawable)
                .into(this)


