package com.fgrutsch.kcfw

fun unhash(hash: String): String {
    return hash.removePrefix("#")
}
