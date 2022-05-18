package com.example.excersise2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.excersise2.R;
import com.example.excersise2.adapter.FoodAdapter;
import com.example.excersise2.databinding.ActivityFoodBinding;
import com.example.excersise2.model.Food;
import com.example.excersise2.network.APIInterface;
import com.example.excersise2.network.ApiClient;
import com.example.excersise2.network.model.BaseResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
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
    EditText edtSearch;

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
        edtSearch = findViewById(R.id.search_view);
        //init
        apiInterface = ApiClient.getClient().create(APIInterface.class);
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    Log.d("ptit", "onEditorAction: " + edtSearch.getText().toString());
                    handleSeach();
                    return true;
                }

                return false;
            }
        });
    }

    private void handleSeach() {
        String key = edtSearch.getText().toString();
        apiInterface.searchFood(key).enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                adapter.setListFood(response.body());
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });
    }

    private void getList() {
        Log.d("ptit", "getList: " );
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
        intent.putExtra("food", (Serializable) food);
        startActivity(intent);
    }

    @Override
    public void onItemClickDelete(Food food) {
        apiInterface.deleteFood(food.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("ptit", "onResponse: sucess");
                Toast.makeText(FoodActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                getList();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("ptit", "onFailure: failure");

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }
}