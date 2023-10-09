package com.fgrutsch.compose

import com.fgrutsch.compose.Route.Companion.DYNAMIC_PART_PREFIX

data class Route(
    val path: String,
    val viewModel: (Route, List<DynamicPart>) -> ViewModel
) {

    data class Part(val value: String, val isDynamic: Boolean)
    data class DynamicPart(val name: String, val value: String)

    private val parts = path.split(PATH_SEPARATOR).map { part ->
        Part(part, part.startsWith(DYNAMIC_PART_PREFIX))
    }

    fun matches(path: String): Boolean {
        val parts = path.split(PATH_SEPARATOR)
        if (parts.size != this.parts.size) return false
        return this.parts.zip(parts).all { (a, b) ->
            a.value == b || a.isDynamic
        }
    }

    fun dynamicParts(path: String): List<DynamicPart> {
        if (!matches(path)) throw IllegalArgumentException("Path does not match route.")
        val parts = path.split(PATH_SEPARATOR)
        return this.parts.zip(parts).mapNotNull { (a, b) ->
            if (a.isDynamic) DynamicPart(a.value, b) else null
        }
    }

    companion object {
        private const val PATH_SEPARATOR = "/"
        const val DYNAMIC_PART_PREFIX = ":"
    }

}

fun List<Route.DynamicPart>.value(id: String): String? =
    find { it.name == id }
        ?.takeUnless { it.value.startsWith(DYNAMIC_PART_PREFIX) }
        ?.value
