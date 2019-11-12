package com.imastudio.mvpapp.model

import com.google.gson.annotations.SerializedName

data class ResponseRegister(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("msg")
	val msg: String? = null
)