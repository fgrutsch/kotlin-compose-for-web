package com.fgrutsch.pf.components

import androidx.compose.runtime.Composable
import com.fgrutsch.pf.PFClasses
import com.fgrutsch.pf.PFComponent
import com.fgrutsch.pf.pfc
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLUListElement


@Composable
fun PFNav(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLElement>? = null,
    content: ContentBuilder<HTMLElement>? = null,
) = Nav(attrs.pfc(PFComponent.NAV, classes), content = content)


@Composable
fun PFNavSection(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLElement>? = null,
    content: ContentBuilder<HTMLElement>? = null,
) = Section(attrs.pfc(PFComponent.NAV_SECTION, classes), content = content)

@Composable
fun PFNavSectionTitle(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLElement>? = null,
    content: ContentBuilder<HTMLElement>? = null,
) = H2(attrs.pfc(PFComponent.NAV_SECTION_TITLE, classes), content = content)

@Composable
fun PFNavList(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLUListElement>? = null,
    content: ContentBuilder<HTMLUListElement>? = null,
) = Ul(attrs.pfc(PFComponent.NAV_LIST, classes), content = content)

@Composable
fun PFNavListItem(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLElement>? = null,
    content: ContentBuilder<HTMLElement>? = null,
) = Li(attrs.pfc(PFComponent.NAV_ITEM, classes), content = content)

@Composable
fun PFNavLink(
    href: String,
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLAnchorElement>? = null,
    content: ContentBuilder<HTMLAnchorElement>? = null,
) = A(href, attrs.pfc(PFComponent.NAV_LINK, classes), content = content)
