package com.example.poscapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;


public class Posc_Home_Activity extends AppCompatActivity {

    private Intent intent;
    static int menu_num;
    static TextView address_text;
    static String PhoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posc_home);
        BottomNavigationView navView = findViewById(R.id.bottom_menu);
        checkPermission();

        DeleteFile();

        address_text = (TextView) findViewById(R.id.addressinput);
        address_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), Posc_Address_Activity.class);
                startActivity(intent);
            }
        });
        address_text.setText(Posc_Address_Input_Activity.finish_address);

        Button button_1 = (Button) findViewById(R.id.btnfood_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_num = 1;
                intent = new Intent(getApplicationContext(), Posc_Select_Activity.class);
                startActivity(intent);
            }
        });

        Button button_2 = (Button) findViewById(R.id.btnfood_2);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_num = 2;
                intent = new Intent(getApplicationContext(), Posc_Select_Activity.class);
                startActivity(intent);
            }
        });

        Button button_3 = (Button) findViewById(R.id.btnfood_3);
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_num = 3;
                intent = new Intent(getApplicationContext(), Posc_Select_Activity.class);
                startActivity(intent);
            }
        });

        Button button_4 = (Button) findViewById(R.id.btnfood_4);
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_num = 4;
                intent = new Intent(getApplicationContext(), Posc_Select_Activity.class);
                startActivity(intent);
            }
        });

        Button button_5 = (Button) findViewById(R.id.btnfood_5);
        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_num = 5;
                intent = new Intent(getApplicationContext(), Posc_Select_Activity.class);
                startActivity(intent);

            }
        });

        Button button_6 = (Button) findViewById(R.id.btnfood_6);
        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_num = 6;
                intent = new Intent(getApplicationContext(), Posc_Select_Activity.class);
                startActivity(intent);
            }
        });

        Button button_7 = (Button) findViewById(R.id.btnfood_7);
        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_num = 7;
                intent = new Intent(getApplicationContext(), Posc_Select_Activity.class);
                startActivity(intent);
            }
        });

        Button button_8 = (Button) findViewById(R.id.btnfood_8);
        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_num = 8;
                intent = new Intent(getApplicationContext(), Posc_Select_Activity.class);
                startActivity(intent);
            }
        });

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_search:
                        intent = new Intent(getApplicationContext(), Posc_Search_Activity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.posc_page_right, R.anim.posc_page_hold);
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

    public void checkPermission(){
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    public void DeleteFile() {
        File file = new File("/data/data/com.example.poscapp/app_raw/menudata.txt");
        if (file.exists()) {
            if (file.delete()) {
               // Toast.makeText(getApplicationContext(), "삭제완료", Toast.LENGTH_LONG).show();
            } else {
               // Toast.makeText(getApplicationContext(), "삭제실패", Toast.LENGTH_LONG).show();
            }
        } else {
            File saveFile = new File("/data/data/com.example.poscapp/app_raw"); // 저장 경로
            if(!saveFile.exists()){ // 폴더 없을 경우
                //Toast.makeText(getApplicationContext(), "파일생성해줬다", Toast.LENGTH_LONG).show();
                saveFile.mkdir(); // 폴더 생성
            } else {
                //Toast.makeText(getApplicationContext(), "폴더있는데?", Toast.LENGTH_LONG).show();
            }
        }
    }
}
