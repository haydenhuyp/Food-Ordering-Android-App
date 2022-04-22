package com.example.finalproject;

import static android.R.color.darker_gray;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AboutScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        Button btnAbout = findViewById(R.id.btnAbout);
        Button btnWelcome = findViewById(R.id.btnWelcome);

        btnAbout.setOnClickListener(new View.OnClickListener(){

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                replaceFragment(new fragmentAbout());
            }
        });
        btnWelcome.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                replaceFragment(new fragmentWelcome());
            }
        });
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
            AboutScreen.this.finish();
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }
}
