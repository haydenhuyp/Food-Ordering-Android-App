package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.drawable.IconCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            MainActivity.this.finish();
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

    public void btnOrderClick(View view){
        Intent intent = new Intent(this, OrderScreen.class);
        startActivity(intent);
    }

    public void btnExitClick(View view){
        MainActivity.this.finish();
        System.exit(0);
    }
}