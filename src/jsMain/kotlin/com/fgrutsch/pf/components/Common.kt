package com.fgrutsch.pf.components

import androidx.compose.runtime.Composable
import com.fgrutsch.pf.PFClasses
import com.fgrutsch.pf.PFComponent
import com.fgrutsch.pf.pfc
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLHRElement
import org.w3c.dom.HTMLHeadingElement

@Composable
fun PFIcon(fontIcon: String, otherClasses: PFClasses = null) {
    I(attrs = {
        classes("fas", fontIcon)
        otherClasses?.forEach { classes(it.value) }
    })
}

@Composable
fun PFContent(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>? = null,
) = Div(attrs.pfc(PFComponent.CONTENT, classes), content = content)

@Composable
fun PFDivider(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLHRElement>? = null,
) = Hr(attrs.pfc(PFComponent.DIVIDER, classes))

@Composable
fun PFTitle(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLHeadingElement>? = null,
    content: ContentBuilder<HTMLHeadingElement>? = null,
) = H1(attrs.pfc(PFComponent.TITLE, classes), content = content)
