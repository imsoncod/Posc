package com.example.poscapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Posc_Search_Activity extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posc_search);

        BottomNavigationView navView = findViewById(R.id.bottom_menu);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        intent = new Intent(getApplicationContext(), Posc_Home_Activity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.posc_page_left, R.anim.posc_page_hold);
                        return true;
                    case R.id.menu_setting:
                        intent = new Intent(getApplicationContext(), Posc_Select_Activity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.posc_page_right, R.anim.posc_page_hold);
                        return true;
                }

                return false;
            }
        });
    }
}
