// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.utils

import android.content.Context
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import androidx.appcompat.widget.AppCompatImageView
import android.widget.ImageView
import com.bumptech.glide.GenericRequestBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.StreamEncoder
import com.bumptech.glide.load.resource.file.FileToStreamDecoder
import com.caverock.androidsvg.SVG
import org.freedombox.freedombox.R
import org.freedombox.freedombox.svg.SvgDecoder
import org.freedombox.freedombox.svg.SvgDrawableTranscoder
import java.io.InputStream

class ImageRenderer(val context: Context) {
    private val requestBuilder: GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> = Glide
            .with(context)
            .using(Glide.buildStreamModelLoader(Uri::class.java, context), InputStream::class.java)
            .from(Uri::class.java)
            .`as`(SVG::class.java)
            .transcode(SvgDrawableTranscoder(), PictureDrawable::class.java)
            .sourceEncoder(StreamEncoder())
            .cacheDecoder(FileToStreamDecoder<SVG>(SvgDecoder()))
            .decoder(SvgDecoder())
            .dontAnimate()
            .error(R.drawable.ic_logo)

    fun loadSvgImageFromURL(url: Uri, imageView: ImageView) {
        requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .load(url)
                .into(imageView)
    }

    fun loadImageFromURL(url: Uri, imageView: AppCompatImageView) {
        Glide.with(context)
                .load(url)
                .error(R.drawable.ic_freedombox_blue)
                .into(imageView)
    }
}
