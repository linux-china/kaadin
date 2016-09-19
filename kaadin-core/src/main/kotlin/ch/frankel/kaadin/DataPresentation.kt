/*
 * Copyright 2016 Nicolas Fränkel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.frankel.kaadin

import com.vaadin.data.*
import com.vaadin.server.*
import com.vaadin.shared.ui.label.*
import com.vaadin.shared.ui.label.ContentMode.*
import com.vaadin.ui.*

/**
 * see http://demo.vaadin.com/sampler/#ui/data-presentation
 */
fun HasComponents.label(content: String? = null,
                        contentMode: ContentMode = TEXT,
                        init: Label.() -> Unit = {}) = Label()
        .apply {
            content?.let { this.value = content }
            this.contentMode = contentMode
        }
        .apply(init)
        .addTo(this)

fun HasComponents.label(contentSource: Property<out Any>,
                        contentMode: ContentMode = TEXT,
                        init: Label.() -> Unit = {}) = Label()
        .apply {
            this.propertyDataSource = contentSource
            this.contentMode = contentMode
        }
        .apply(init)
        .addTo(this)

private fun <M : AbstractMedia> M.process(container: HasComponents,
                                          caption: String?,
                                          source: Resource?,
                                          init: M.() -> Unit): M {
    return apply {
        caption?.let { this.caption = caption }
        source?.let { setSource(source) }
    }
            .apply(init)
            .addTo(container)
}

fun HasComponents.audio(caption: String? = null,
                        source: Resource? = null,
                        init: Audio.() -> Unit = {}) = Audio()
        .process(this, caption, source, init)

fun HasComponents.video(caption: String? = null,
                        source: Resource? = null,
                        init: Video.() -> Unit = {}) = Video()
        .process(this, caption, source, init)

private fun <E : AbstractEmbedded> E.process(container: HasComponents,
                                             caption: String?,
                                             source: Resource?,
                                             init: E.() -> Unit): E {
    return apply {
        caption?.let { this.caption = caption }
        source?.let { setSource(source) }
    }
            .apply(init)
            .addTo(container)
}

fun HasComponents.image(caption: String? = null,
                        source: Resource? = null,
                        init: Image.() -> Unit = {}) = Image()
        .process(this, caption, source, init)

fun HasComponents.flash(caption: String? = null,
                        source: Resource? = null,
                        init: Flash.() -> Unit = {}) = Flash()
        .process(this, caption, source, init)

fun HasComponents.browserFrame(caption: String? = null,
                               source: Resource? = null,
                               init: BrowserFrame.() -> Unit = {}) = BrowserFrame()
        .process(this, caption, source, init)
