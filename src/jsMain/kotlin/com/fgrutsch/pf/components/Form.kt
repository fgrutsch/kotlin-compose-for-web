package com.fgrutsch.pf.components

import androidx.compose.runtime.Composable
import com.fgrutsch.pf.PFClasses
import com.fgrutsch.pf.PFComponent
import com.fgrutsch.pf.pfc
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLFormElement
import org.w3c.dom.HTMLSpanElement

@Composable
fun PFForm(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLFormElement>? = null,
    content: ContentBuilder<HTMLFormElement>? = null,
) = Form(attrs = attrs.pfc(PFComponent.FORM, classes), content = content)

@Composable
fun PFFormGroup(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>? = null,
) = Div(attrs.pfc(PFComponent.FORM_GROUP, classes), content = content)


@Composable
fun PFFormGroupControl(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>? = null,
) = Div(attrs.pfc(PFComponent.FORM_GROUP_CONTROL, classes), content = content)

@Composable
fun PFFormGroupLabel(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>? = null,
) = Div(attrs.pfc(PFComponent.FORM_GROUP_LABEL, classes), content = content)

@Composable
fun PFFormLabelText(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLSpanElement>? = null,
    content: ContentBuilder<HTMLSpanElement>? = null,
) = Span(attrs.pfc(PFComponent.FORM_LABEL_TEXT, classes), content = content)

@Composable
fun PFFormLabelRequired(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLSpanElement>? = null,
    content: ContentBuilder<HTMLSpanElement>? = null,
) = Span(attrs.pfc(PFComponent.FORM_LABEL_REQUIRED, classes), content = content)

@Composable
fun PFFormControl(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLSpanElement>? = null,
    content: ContentBuilder<HTMLSpanElement>? = null,
) = Span(attrs.pfc(PFComponent.FORM_CONTROL, classes), content = content)

@Composable
fun PFFormControlUtilities(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLSpanElement>? = null,
    content: ContentBuilder<HTMLSpanElement>? = null,
) = Span(attrs.pfc(PFComponent.FORM_CONTROL_UTILITIES, classes), content = content)

@Composable
fun PFFormControlIcon(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLSpanElement>? = null,
    content: ContentBuilder<HTMLSpanElement>? = null,
) = Span(attrs.pfc(PFComponent.FORM_CONTROL_ICON, classes), content = content)

@Composable
fun PFFormHelperText(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>? = null,
) = Div(attrs.pfc(PFComponent.FORM_HELPER_TEXT, classes), content = content)

@Composable
fun PFFormActions(
    classes: PFClasses = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>? = null,
) = Div(attrs.pfc(PFComponent.FORM_ACTIONS, classes), content = content)
