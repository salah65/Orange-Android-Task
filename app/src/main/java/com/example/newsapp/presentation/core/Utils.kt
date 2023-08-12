package com.example.newsapp.presentation.core

import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import coil.load
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.*

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

@BindingAdapter("android:date")
fun parseDate(textView: TextView, date: String) {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:aa", Locale.US)
    textView.text = formatter.format(parser.parse(date))
}
