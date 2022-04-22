package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Array;
import java.util.ArrayList;

public class OrderScreen extends AppCompatActivity {

    private RecyclerView oRecyclerView;
    private OrderItemsAdapter oAdapter;
    private RecyclerView.LayoutManager oLayoutManager;
    private ArrayList<OrderItem> oItemList;
    private ArrayList<OrderItem> orderedItemList = new ArrayList<>();
    private boolean[] itemTracker = {false, false, false, false, false, false, false, false, false, false};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_screen);

        MenuDb db = new MenuDb(this);
        oItemList = db.getMenuItems();

        oRecyclerView = findViewById(R.id.rvOrderMenu);
        oRecyclerView.setHasFixedSize(true);
        oLayoutManager = new LinearLayoutManager(this);
        oAdapter = new OrderItemsAdapter(oItemList);

        oRecyclerView.setLayoutManager(oLayoutManager);
        oRecyclerView.setAdapter(oAdapter);

        oAdapter.setOnItemClickListener(new OrderItemsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                oItemList.get(position).changeCheckBox();
                oAdapter.notifyItemChanged(position);
                itemTracker[position] = !itemTracker[position];
            }
        });
    }

    public void btnSendOrderClick(View view){

        for(int i=0; i<10; i++){
            if(itemTracker[i]){
                orderedItemList.add(oItemList.get(i));
            }
        }

        Intent intent = new Intent(this, EnterInfoScreen.class);
        intent.putExtra("OrderedItemList", orderedItemList);
        startActivity(intent);
    }

    // option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.item_home){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (id==R.id.item_order){
            Intent intent = new Intent(this, OrderScreen.class);
            startActivity(intent);
        }
        if (id==R.id.item_about){
            Intent intent = new Intent(this, AboutScreen.class);
            startActivity(intent);
        }
        if (id==R.id.item_exit){
            OrderScreen.this.finish();
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }
}
