package com.example.excersise2.network;

import com.example.excersise2.model.Customer;
import com.example.excersise2.model.Food;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    String path = "api/v1/food";

    //lấy toàn bộ các món ăn
    @GET(path +"/getAll")
    Call<List<Food>> getAllFood();

    @GET(path +"/getById/{id}")
    Call<Food> getFoodById(@Path("id") String id);

    @POST(path + "/save")
    Call<Food> saveFood(@Body Food food);

    @POST(path + "/edit")
    Call<Food> editFood(@Body Food food);

    @GET(path + "/delete/{id}")
    Call<Void> deleteFood(@Path("id") long id);

    @GET(path + "/search/{key}")
    Call<List<Food>> searchFood(@Path("key") String keyword);

}


