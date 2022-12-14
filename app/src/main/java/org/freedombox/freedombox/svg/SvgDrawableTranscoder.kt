// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.svg

import android.graphics.drawable.PictureDrawable
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder
import com.caverock.androidsvg.SVG

class SvgDrawableTranscoder : ResourceTranscoder<SVG, PictureDrawable> {

    override fun getId() = "org.freedombox.freedombox"

    override fun transcode(toTranscode: Resource<SVG>): Resource<PictureDrawable> {
        val svg = toTranscode.get()
        val picture = svg.renderToPicture()
        val drawable = PictureDrawable(picture)

        return SimpleResource<PictureDrawable>(drawable)
    }
}
