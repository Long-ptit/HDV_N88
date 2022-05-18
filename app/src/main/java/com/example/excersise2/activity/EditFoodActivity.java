package com.example.excersise2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.excersise2.R;
import com.example.excersise2.model.Food;
import com.example.excersise2.network.APIInterface;
import com.example.excersise2.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditFoodActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtName, edtPrice, edtUrl, edtDes;
    private Button btnConfirm;
    private APIInterface apiInterface;
    private Food myFood;
    private EditText searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);
        initView();
        handleIntent();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        myFood = (Food) intent.getSerializableExtra("food");

        //set value
        edtName.setText(myFood.getName());
        edtUrl.setText(myFood.getUrl());
        edtPrice.setText(myFood.getPrice() + "");
        edtDes.setText(myFood.getDes());
    }

    private void initView() {
        edtName = findViewById(R.id.edt_name);
        edtPrice = findViewById(R.id.edt_price);
        edtUrl = findViewById(R.id.edt_url_image);
        btnConfirm = findViewById(R.id.btn_confirm);
        edtDes = findViewById(R.id.edt_des);
        searchView = findViewById(R.id.search_view);

        //init retrofit and onclick
        btnConfirm.setOnClickListener(this);
        apiInterface = ApiClient.getClient().create(APIInterface.class);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm: {
                handleBtnConfirm();
            }
        }
    }

    private void handleBtnConfirm() {
        Food food = new Food();
        food.setId(myFood.getId());
        food.setName(edtName.getText().toString());
        food.setDes(edtDes.getText().toString());
        food.setUrl(edtUrl.getText().toString());
        food.setPrice(Long.parseLong(edtPrice.getText().toString()));

        apiInterface.editFood(food).enqueue(new Callback<Food>() {
            @Override
            public void onResponse(Call<Food> call, Response<Food> response) {
                Toast.makeText(EditFoodActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Food> call, Throwable t) {

            }
        });
    }
}