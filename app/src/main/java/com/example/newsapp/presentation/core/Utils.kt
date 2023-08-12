package com.example.newsapp.presentation.core

import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import coil.load
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@BindingAdapter("android:url")
fun loadImageByUrl(imageView: ImageView, url: String) {
    imageView.load(url)
}

fun EditText.getQueryTextChangeStateFlow(): StateFlow<String> {

    val query = MutableStateFlow("")

    doOnTextChanged { text, start, before, count ->
        query.value = text.toString()
    }
    return query

}
