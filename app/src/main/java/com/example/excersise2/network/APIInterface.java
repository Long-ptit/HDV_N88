package com.example.excersise2.network;

import com.example.excersise2.model.Customer;
import com.example.excersise2.model.Food;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIInterface {

    String path = "api/v1/food";
    //lấy toàn bộ các món ăn
    @GET( path +"/getAll")
    Call<List<Food>> getAllFood();

    @FormUrlEncoded
    @POST("v1/ephemeral_keys")
    Call<JsonObject> getEmprieKey(
            @Header("Authorization") String author,
            @Field("customer") String customer_id,
            @Header("Stripe-Version") String ver
    );

    @FormUrlEncoded
    @POST("v1/payment_intents")
    Call<JsonObject> createPayment(
            @Header("Authorization") String author,
            @Field("customer") String customer_id,
            @Field("amount") String amount,
            @Field("currency") String currency,
            @Field("automatic_payment_methods[enabled]") String state

    );

}


