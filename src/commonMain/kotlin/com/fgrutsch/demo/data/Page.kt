package com.fgrutsch.demo.data

data class Page<T>(
    val items: List<T>,
    val total: Int,
    val pageNumber: Int,
    val pageSize: Int
)
