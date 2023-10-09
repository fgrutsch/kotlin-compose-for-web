package com.fgrutsch.pf.components

import androidx.compose.runtime.Composable
import com.fgrutsch.pf.PFClasses
import com.fgrutsch.pf.PFComponent
import com.fgrutsch.pf.pfc
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLImageElement

@Composable
fun PFMasthead(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLElement>? = null,
    content: ContentBuilder<HTMLElement>? = null,
) = Header(attrs.pfc(PFComponent.MASTHEAD, classes), content = content)

@Composable
fun PFMastheadToggle(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLElement>? = null,
    content: ContentBuilder<HTMLElement>? = null,
) = Span(attrs.pfc(PFComponent.MASTHEAD_TOGGLE, classes), content = content)

@Composable
fun PFMastheadMain(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>? = null,
) = Div(attrs.pfc(PFComponent.MASTHEAD_MAIN, classes), content = content)

@Composable
fun PFMastheadBrand(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLAnchorElement>? = null,
    content: ContentBuilder<HTMLAnchorElement>? = null,
) = A("#/", attrs.pfc(PFComponent.MASTHEAD_BRAND, classes), content = content)

@Composable
fun PFMastheadBrandImg(
    src: String,
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLImageElement>? = null
) = Img(src, "", attrs.pfc(PFComponent.BRAND, classes))

@Composable
fun PFMastheadContent(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>? = null,
) = Div(attrs.pfc(PFComponent.MASTHEAD_CONTENT, classes), content = content)
