package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ConfirmScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);

        // notification

        NotificationCompat.Builder builder = new NotificationCompat.Builder(ConfirmScreen.this, "TrainOrderNotif");
        builder.setContentTitle("We got your order!");
        builder.setContentText("Hi, we have got your order. It will be ready in a few minutes. Thank you.");
        builder.setSmallIcon(R.drawable.icon);
        builder.setAutoCancel(true);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(ConfirmScreen.this);
        managerCompat.notify(1, builder.build());

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
            ConfirmScreen.this.finish();
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

    public void btnOrderAgainClick(View view){
        Intent intent = new Intent(this, OrderScreen.class);
        startActivity(intent);
    }

    public void btnHomeClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
