// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.svg

import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.resource.SimpleResource
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import java.io.InputStream

class SvgDecoder : ResourceDecoder<InputStream, SVG> {

    override fun decode(source: InputStream?, width: Int, height: Int) = try {
        SimpleResource<SVG>(SVG.getFromInputStream(source))
    } catch (e: SVGParseException) {
        null
    }

    override fun getId() = "org.freedombox.freedombox.svg"
}
