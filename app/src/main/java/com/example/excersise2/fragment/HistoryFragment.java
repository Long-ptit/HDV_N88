package com.example.excersise2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.excersise2.R;
import com.example.excersise2.dal.SqliteHelper;
import com.example.excersise2.model.Item;

import java.util.List;

public class HistoryFragment extends Fragment {

    private SqliteHelper sqliteHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sqliteHelper = new SqliteHelper(getContext());
        List<Item> list = sqliteHelper.getAll();
    }
}