package com.example.newsapp.data.network.response

import com.google.gson.annotations.SerializedName

open class BaseResponse(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
