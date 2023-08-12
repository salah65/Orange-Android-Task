package com.example.newsapp.domain.core


enum class SortBy(val value: String) {
    PUBLISHED_AT("publishedAt")

}

enum class SearchIn(val value: String) {
    TITLE("title"),
    DESCRIPTION("description"),
    CONTENT("content"),
}