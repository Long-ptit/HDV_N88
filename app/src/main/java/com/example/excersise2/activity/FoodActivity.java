package com.example.excersise2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.excersise2.R;
import com.example.excersise2.adapter.FoodAdapter;
import com.example.excersise2.databinding.ActivityFoodBinding;
import com.example.excersise2.model.Food;
import com.example.excersise2.network.APIInterface;
import com.example.excersise2.network.ApiClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener, FoodAdapter.IListener {
    FloatingActionButton fap;
    RecyclerView rcv;
    FoodAdapter adapter;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        initView();
        initListener();
        initRecycleView();
    }

    private void initListener() {
        fap.setOnClickListener(this);
    }

    private void initRecycleView() {
        adapter = new FoodAdapter(this, this);
        getList();
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv.setLayoutManager(manager);
        rcv.setAdapter(adapter);
    }

    private void initView() {
        fap = findViewById(R.id.fab_add);
        rcv = findViewById(R.id.rcv);

        //init
        apiInterface = ApiClient.getClient().create(APIInterface.class);
    }

    private void getList() {
        apiInterface.getAllFood().enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                List<Food> list = response.body();
                adapter.setListFood(list);
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == fap) {
            Intent intent = new Intent(FoodActivity.this, AddFoodActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public void onItemClick(Food food) {
        Toast.makeText(this, food.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EditFoodActivity.class);
        startActivity(intent);
    }
}