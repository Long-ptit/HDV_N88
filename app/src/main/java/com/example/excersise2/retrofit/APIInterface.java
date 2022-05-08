package com.example.excersise2.retrofit;

import com.example.excersise2.model.Customer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIInterface {
    @POST("/v1/customers")
    Call<JsonObject> getCustomer(
            @Header("Authorization") String author
    );

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


