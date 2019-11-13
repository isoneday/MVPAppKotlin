package com.imastudio.mvpapp.model.modelwisata

import com.google.gson.annotations.SerializedName

data class ResponseWisata(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)