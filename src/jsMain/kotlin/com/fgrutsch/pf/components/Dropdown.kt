package com.fgrutsch.pf.components

import androidx.compose.runtime.Composable
import com.fgrutsch.pf.PFClasses
import com.fgrutsch.pf.PFComponent
import com.fgrutsch.pf.pfc
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
fun PFDropdown(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>? = null,
) = Div(attrs.pfc(PFComponent.DROPDOWN, classes), content = content)

@Composable
fun PFDropdownToggle(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLButtonElement>? = null,
    content: ContentBuilder<HTMLButtonElement>? = null,
) = Button(attrs.pfc(PFComponent.DROPDOWN_TOGGLE, classes?.let { it + listOf(PFComponent.BUTTON) }), content = content)

@Composable
fun PFDropdownToggleIcon(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLSpanElement>? = null,
    content: ContentBuilder<HTMLSpanElement>? = null,
) = Span(attrs.pfc(PFComponent.DROPDOWN_TOGGLE_ICON, classes), content = content)


@Composable
fun PFDropdownMenu(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLUListElement>? = null,
    content: ContentBuilder<HTMLUListElement>? = null,
) = Ul(attrs.pfc(PFComponent.DROPDOWN_MENU, classes), content = content)

@Composable
fun PFDropdownMenuItem(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLButtonElement>? = null,
    content: ContentBuilder<HTMLButtonElement>? = null,
) = Button(attrs.pfc(PFComponent.DROPDOWN_MENU_ITEM, classes), content = content)
