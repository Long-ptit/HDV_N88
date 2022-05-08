package com.example.excersise2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.excersise2.R;

public class EditFoodActivity extends AppCompatActivity {
    private EditText edtName, edtPrice, edtUrl;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);
        initView();
    }

    private void initView() {
        edtName = findViewById(R.id.edt_name);
        edtPrice = findViewById(R.id.edt_price);
        edtUrl = findViewById(R.id.edt_url_image);
        btnConfirm = findViewById(R.id.btn_confirm);
    }
}