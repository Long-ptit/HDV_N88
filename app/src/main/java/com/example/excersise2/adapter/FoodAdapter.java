package com.example.excersise2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
        notifyDataSetChanged();
    }

    public interface IListener {
        void onItemClick(Food food);
        void onItemClickDelete(Food food);
    }


    @NonNull
    @Override
    public FoodAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_food, parent, false);
        return new FoodAdapterViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FoodAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Food food = mList.get(position);
        if (food == null) return;

        holder.tvName.setText(food.getName());
        holder.tvPrice.setText(food.getPrice() + " K");
        holder.tvDes.setText(food.getDes());
        Glide.with(mContext).load(food.getUrl()).into(holder.imgFood);
        holder.layout.setOnClickListener(view -> listener.onItemClick(food));

        holder.btnDelete.setOnClickListener(view -> listener.onItemClickDelete(food));
    }

    @Override
    public int getItemCount() {
        if (mList != null) return mList.size();
        return 0;
    }

    public class FoodAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvPrice, tvDes;
        public LinearLayout layout;
        public ImageView imgFood;
        public Button btnDelete;

        public FoodAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_food);
            tvPrice = itemView.findViewById(R.id.tv_price_food);
            tvDes = itemView.findViewById(R.id.tv_des_food);
            layout = itemView.findViewById(R.id.layout_item);
            imgFood = itemView.findViewById(R.id.img_food);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }


}
