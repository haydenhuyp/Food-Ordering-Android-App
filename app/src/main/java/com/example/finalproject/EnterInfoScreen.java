package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class EnterInfoScreen extends AppCompatActivity {


    public ArrayList<OrderItem> orderItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_info);

        TextView txtOrder = findViewById(R.id.txtOrder);
        TextView txtTotal = findViewById(R.id.txtTotal);
        orderItems = (ArrayList<OrderItem>) getIntent().getSerializableExtra("OrderedItemList");

        double total = 0;
        for (int i = 0; i < orderItems.size(); i++) {
            txtOrder.append(orderItems.get(i).getItemName()  + ": " + orderItems.get(i).getItemPriceDbl() + "\n");
            total += orderItems.get(i).getItemPriceDbl();
        }
        txtTotal.append("$" + total);


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
            EnterInfoScreen.this.finish();
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

    public void btnConfirm_Click(View view){
        Intent intent = new Intent(this, ConfirmScreen.class);
        startActivity(intent);
    }
}
