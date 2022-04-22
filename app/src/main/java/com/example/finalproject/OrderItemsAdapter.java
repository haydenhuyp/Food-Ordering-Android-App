package com.example.finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class OrderItemsAdapter extends RecyclerView.Adapter<OrderItemsAdapter.OrderViewHolder> {
    private ArrayList<OrderItem> oItemList;
    private OnItemClickListener oListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        oListener = listener;
    }


    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        public TextView oItemName;
        public TextView oItemPrice;
        public CheckBox oCheckBox;

        public OrderViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            oItemName = itemView.findViewById(R.id.tvOrderItemName);
            oItemPrice = itemView.findViewById(R.id.tvOrderItemPrice);
            oCheckBox = itemView.findViewById(R.id.cbIsOrdered);

            oCheckBox.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public OrderItemsAdapter(ArrayList<OrderItem> itemList) {
        oItemList = itemList;
    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        OrderViewHolder ovh = new OrderViewHolder(v, oListener);
        return ovh;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderItem currentItem = oItemList.get(position);

        holder.oItemName.setText(currentItem.getItemName());
        holder.oItemPrice.setText(currentItem.getItemPriceStr());
        holder.oCheckBox.setChecked(currentItem.getIsChecked());
    }

    @Override
    public int getItemCount() {
        return oItemList.size();
    }

}
