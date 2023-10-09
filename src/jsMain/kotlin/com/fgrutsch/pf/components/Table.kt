package com.fgrutsch.pf.components

import androidx.compose.runtime.Composable
import com.fgrutsch.pf.PFClasses
import com.fgrutsch.pf.PFComponent
import com.fgrutsch.pf.pfc
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLTableCellElement
import org.w3c.dom.HTMLTableElement
import org.w3c.dom.HTMLTableRowElement
import org.w3c.dom.HTMLTableSectionElement

@Composable
fun PFTable(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLTableElement>? = null,
    content: ContentBuilder<HTMLTableElement>? = null,
) = Table(attrs.pfc(PFComponent.TABLE, classes), content = content)

@Composable
fun PFTableHead(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLTableSectionElement>? = null,
    content: ContentBuilder<HTMLTableSectionElement>? = null,
) = Thead(attrs.pfc(PFComponent.TABLE_HEAD, classes), content = content)

@Composable
fun PFTableRow(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLTableRowElement>? = null,
    content: ContentBuilder<HTMLTableRowElement>? = null,
) = Tr(attrs.pfc(PFComponent.TABLE_TR, classes), content = content)

@Composable
fun PFTableRowHeader(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLTableCellElement>? = null,
    content: ContentBuilder<HTMLTableCellElement>? = null,
) = Th(attrs.pfc(PFComponent.TABLE_TH, classes), content = content)

@Composable
fun PFTableBody(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLTableSectionElement>? = null,
    content: ContentBuilder<HTMLTableSectionElement>? = null,
) = Tbody(attrs.pfc(PFComponent.TABLE_BODY, classes), content = content)

@Composable
fun PFTableRowData(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLTableCellElement>? = null,
    content: ContentBuilder<HTMLTableCellElement>? = null,
) = Td(attrs.pfc(PFComponent.TABLE_TD, classes), content = content)

@Composable
fun PFTableRowDataAction(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLTableCellElement>? = null,
    content: ContentBuilder<HTMLTableCellElement>? = null,
) = Td(attrs.pfc(PFComponent.TABLE_TD, classes?.let { it + listOf(PFComponent.TABLE_ACTION) }), content = content)
