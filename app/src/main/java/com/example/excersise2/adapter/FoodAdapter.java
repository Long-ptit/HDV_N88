package com.example.excersise2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.excersise2.R;
import com.example.excersise2.model.Food;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodAdapterViewHolder> {

    private List<Food> mList;
    private Context mContext;
    private IListener listener;

    public FoodAdapter(Context context, IListener listener) {
        this.mContext = context;
        this.listener = listener;
    }

    public void setListFood(List<Food> list) {
        this.mList = list;
    }

    public interface IListener {
        void onItemClick(Food food);
    }


    @NonNull
    @Override
    public FoodAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_food, parent, false);
        return new FoodAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(mList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList != null) return mList.size();
        return 0;
    }

    public class FoodAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvPrice, tvDes;
        public LinearLayout layout;

        public FoodAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_food);
            tvPrice = itemView.findViewById(R.id.tv_price_food);
            tvDes = itemView.findViewById(R.id.tv_des_food);
            layout = itemView.findViewById(R.id.layout_item);
        }
    }


}
