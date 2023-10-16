package com.fgrutsch.demo

import org.w3c.dom.Location

fun Location.hashPath(): String = hash.removePrefix("#").ifEmpty { "/" }

/**
 * Bridge to JS require
 */
external fun require(module: String): dynamic
