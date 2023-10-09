package com.fgrutsch.pf.components

import androidx.compose.runtime.Composable
import com.fgrutsch.pf.PFClasses
import com.fgrutsch.pf.PFComponent
import com.fgrutsch.pf.pfc
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Nav
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement

@Composable
fun PFPagination(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>? = null,
) = Div(attrs.pfc(PFComponent.PAGINATION, classes), content = content)

@Composable
fun PFPaginationNav(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLElement>? = null,
    content: ContentBuilder<HTMLElement>? = null,
) = Nav(attrs.pfc(PFComponent.PAGINATION_NAV, classes), content = content)

@Composable
fun PFPaginationNavControl(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>? = null,
) = Div(attrs.pfc(PFComponent.PAGINATION_NAV_CONTROL, classes), content = content)

@Composable
fun PFPaginationNavPageSelect(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>? = null,
) = Div(attrs.pfc(PFComponent.PAGINATION_NAV_PAGE_SELECT, classes), content = content)
