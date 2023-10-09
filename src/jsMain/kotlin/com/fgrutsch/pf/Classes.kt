package com.fgrutsch.pf

import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.w3c.dom.Element

interface PFClass {
    val value: String
}

typealias PFClasses = List<PFClass>?

fun <T : Element> AttrBuilderContext<T>?.pfc(
    component: PFComponent,
    otherClasses: PFClasses = null,
): AttrBuilderContext<T> = {
    this@pfc?.let { this.it() }
    classes(component.value)
    otherClasses?.let { other -> classes(other.map { it.value }) }
}

enum class PFModifier(override val value: String) : PFClass {
    ALIGN_RIGHT("pf-m-align-right"),
    BOTTOM("pf-m-bottom"),
    COLLAPSED("pf-m-collapsed"),
    COMPACT("pf-m-compact"),
    CURRENT("pf-m-current"),
    ERROR("pf-m-error"),
    EXPANDED("pf-m-expanded"),
    HORIZONTAL("pf-m-horizontal"),
    LIGHT("pf-m-light"),
    NO_PADDING("pf-m-no-padding"),
    PLAIN("pf-m-plain"),
    PRIMARY("pf-m-primary"),
    STATUS("pf-m-status"),
    XL_3("pf-m-3xl"),
}

enum class PFUtil(override val value: String): PFClass {
    PR_MD("pf-v5-u-pr-md"),
}

enum class PFComponent(override val value: String) : PFClass {
    BRAND("pf-v5-c-brand"),
    BUTTON("pf-v5-c-button"),
    CONTENT("pf-v5-c-content"),
    DIVIDER("pf-v5-c-divider"),

    CARD("pf-v5-c-card"),
    CARD_BODY("pf-v5-c-card__body"),

    DROPDOWN("pf-v5-c-dropdown"),
    DROPDOWN_TOGGLE("pf-v5-c-dropdown__toggle"),
    DROPDOWN_TOGGLE_ICON("pf-v5-c-dropdown__toggle-icon"),
    DROPDOWN_MENU("pf-v5-c-dropdown__menu"),
    DROPDOWN_MENU_ITEM("pf-v5-c-dropdown__menu-item"),

    FORM("pf-v5-c-form"),
    FORM_GROUP("pf-v5-c-form__group"),
    FORM_GROUP_CONTROL("pf-v5-c-form__group-control"),
    FORM_GROUP_LABEL("pf-v5-c-form__group-label"),
    FORM_LABEL_TEXT("pf-v5-c-form__label-text"),
    FORM_LABEL_REQUIRED("pf-v5-c-form__label-required"),
    FORM_CONTROL("pf-v5-c-form-control"),
    FORM_CONTROL_UTILITIES("pf-v5-c-form-control__utilities"),
    FORM_CONTROL_ICON("pf-v5-c-form-control__icon"),
    FORM_HELPER_TEXT("pf-v5-c-form__helper-text"),
    FORM_ACTIONS("pf-v5-c-form__actions"),

    HELPER_TEXT("pf-v5-c-helper-text"),
    HELPER_TEXT_ITEM("pf-v5-c-helper-text__item"),
    HELPER_TEXT_ITEM_TEXT("pf-v5-c-helper-text__item-text"),

    MASTHEAD("pf-v5-c-masthead"),
    MASTHEAD_TOGGLE("pf-v5-c-masthead__toggle"),
    MASTHEAD_MAIN("pf-v5-c-masthead__main"),
    MASTHEAD_BRAND("pf-v5-c-masthead__brand"),
    MASTHEAD_CONTENT("pf-v5-c-masthead__content"),

    NAV("pf-v5-c-nav"),
    NAV_SECTION("pf-v5-c-nav__section"),
    NAV_SECTION_TITLE("pf-v5-c-nav__section-title"),
    NAV_LIST("pf-v5-c-nav__list"),
    NAV_ITEM("pf-v5-c-nav__item"),
    NAV_LINK("pf-v5-c-nav__link"),

    OPTIONS_MENU("pf-v5-c-options-menu"),
    OPTIONS_MENU_TOGGLE_TEXT("pf-v5-c-options-menu__toggle-text"),

    PAGE("pf-v5-c-page"),
    PAGE_MAIN("pf-v5-c-page__main"),
    PAGE_MAIN_SECTION("pf-v5-c-page__main-section"),
    PAGE_SIDEBAR("pf-v5-c-page__sidebar"),
    PAGE_SIDEBAR_BODY("pf-v5-c-page__sidebar-body"),

    PAGINATION("pf-v5-c-pagination"),
    PAGINATION_NAV("pf-v5-c-pagination__nav"),
    PAGINATION_NAV_CONTROL("pf-v5-c-pagination__nav-control"),
    PAGINATION_NAV_PAGE_SELECT("pf-v5-c-pagination__nav-page-select"),

    TABLE("pf-v5-c-table"),
    TABLE_ACTION("pf-v5-c-table__action"),
    TABLE_BODY("pf-v5-c-table__tbody"),
    TABLE_HEAD("pf-v5-c-table__thead"),
    TABLE_TD("pf-v5-c-table__td"),
    TABLE_TH("pf-v5-c-table__th"),
    TABLE_TR("pf-v5-c-table__tr"),

    TITLE("pf-v5-c-title"),

    TOOLBAR("pf-v5-c-toolbar"),
    TOOLBAR_CONTENT("pf-v5-c-toolbar__content"),
    TOOLBAR_CONTENT_SECTION("pf-v5-c-toolbar__content-section"),
    TOOLBAR_ITEM("pf-v5-c-toolbar__item"),
}
