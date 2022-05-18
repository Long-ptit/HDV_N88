package com.example.excersise2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

public class AddFoodActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtName, edtPrice, edtUrl, edtDes;
    private Button btnConfirm;
    private APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        initView();

    }

    private void initView() {
        edtName = findViewById(R.id.edt_name);
        edtPrice = findViewById(R.id.edt_price);
        edtUrl = findViewById(R.id.edt_url_image);
        btnConfirm = findViewById(R.id.btn_confirm);
        edtDes = findViewById(R.id.edt_des);

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
        food.setName(edtName.getText().toString());
        food.setDes(edtDes.getText().toString());
        food.setUrl(edtUrl.getText().toString());
        food.setPrice(Long.parseLong(edtPrice.getText().toString()));

        apiInterface.saveFood(food).enqueue(new Callback<Food>() {
            @Override
            public void onResponse(Call<Food> call, Response<Food> response) {
                Toast.makeText(AddFoodActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Food> call, Throwable t) {

            }
        });
    }
}