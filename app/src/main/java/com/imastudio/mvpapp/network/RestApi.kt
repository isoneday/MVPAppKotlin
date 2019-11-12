package com.imastudio.mvpapp.network

import com.imastudio.mvpapp.model.ResponseRegister
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RestApi {

    @FormUrlEncoded
    @POST("registeruser.php")
    fun registerUser(
        @Field("vsnama") vsnama:String?,
        @Field("vsusia") vsusia:String?,
        @Field("vsalamat") vsalamat:String?,
        @Field("vsnotelp") vsnotelp:String?,
        @Field("vsjenkel") vsjenkel:String?,
        @Field("vsusername") vsusername:String?,
        @Field("vslevel") vslevel:String?,
        @Field("vspassword") vspassword:String?
    ) : Call<ResponseRegister>

}