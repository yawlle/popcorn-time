package com.example.popcorntime.presentation.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.popcorntime.R
import kotlinx.coroutines.flow.Flow

fun Fragment.observeNavBack(
    state: Flow<Boolean>,
) {
    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        state.collect {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}

@BindingAdapter("imageUrl")
fun ImageView.setImageByLoader(
    imageUrl: String?,
) {
    val imageLoader: ImageLoader = getImageLoaderBuilder()
    imageLoader.enqueue(
        ImageRequest
            .Builder(this.context)
            .placeholder(R.drawable.placeholder_movie)
            .error(R.drawable.placeholder_movie)
            .data(imageUrl)
            .target(this)
            .build()
    )
}

private fun ImageView.getImageLoaderBuilder() = ImageLoader
    .Builder(context)
    .components { add(SvgDecoder.Factory()) }
    .crossfade(true)
    .build()

@BindingAdapter("isVisible")
fun View.isVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}