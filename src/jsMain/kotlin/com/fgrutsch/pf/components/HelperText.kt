package com.fgrutsch.pf.components

import androidx.compose.runtime.Composable
import com.fgrutsch.pf.PFClasses
import com.fgrutsch.pf.PFComponent
import com.fgrutsch.pf.pfc
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLSpanElement

@Composable
fun PFHelperText(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>? = null,
) = Div(attrs.pfc(PFComponent.HELPER_TEXT, classes), content = content)

@Composable
fun PFHelperTextItem(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>? = null,
) = Div(attrs.pfc(PFComponent.HELPER_TEXT_ITEM, classes), content = content)

@Composable
fun PFHelperTextItemText(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLSpanElement>? = null,
    content: ContentBuilder<HTMLSpanElement>? = null,
) = Span(attrs.pfc(PFComponent.HELPER_TEXT_ITEM_TEXT, classes), content = content)
