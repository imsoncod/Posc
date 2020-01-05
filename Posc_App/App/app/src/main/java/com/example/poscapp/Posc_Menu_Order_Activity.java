package com.example.poscapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Posc_Menu_Order_Activity extends AppCompatActivity {
    private Intent intent;
    static String str_order_title;
    static String str_order_price;
    static String str_order_option;
    static String str_order_option_name;
    static String str_order_option_price;
    static String str_order_finish_price;
    TextView order_finish_price;
    private WebView sit_view;
    int sum = 0;
    Boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_order_table);
        checkPermission();
        SetOrderText(str_order_title, str_order_price);
        AddOption();

        Button add = (Button)findViewById(R.id.order_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check == true) {
                    Menu_Write_File(str_order_option_name, sum+"", 1+"");
                    Posc_Menu_Finish_Order_Activity.GetValues(str_order_title, sum);
                   // init_webView_1(str_order_option_name, sum+"");
                }
                if(check == false) {
                    Menu_Write_File(str_order_title, str_order_price+"", 1+"");
                    Posc_Menu_Finish_Order_Activity.GetValues(str_order_title, Integer.parseInt(str_order_price));
                    //init_webView_1(str_order_title, str_order_price);
                }
                intent = new Intent(getApplicationContext(), Posc_Menu_Finish_Order_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.posc_page_right, R.anim.posc_page_hold);
                finish();
            }
        });

        Button canc = (Button)findViewById(R.id.order_cancle);
        canc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    intent = new Intent(getApplicationContext(), Posc_Menu_Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.posc_page_right, R.anim.posc_page_hold);
                    finish();
            }
        });
    }

    public void init_webView_1(String name, String price) {

        // WebView 설정
        sit_view = (WebView) findViewById(R.id.menu_oracle);
        sit_view.getSettings().setJavaScriptEnabled(true);
        sit_view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        sit_view.getSettings().setSupportMultipleWindows(true);
        sit_view.loadUrl("http://183.91.253.81:3100/WebTest/NewFile4.jsp?phone=010-9729-2751&name="+name+"&price="+price+"&count=1");
        sit_view.getSettings().setLoadWithOverviewMode(true);
        sit_view.setWebChromeClient(new WebChromeClient());
        sit_view.setVerticalScrollBarEnabled(false);
        sit_view.setHorizontalScrollBarEnabled(false);
        sit_view.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        sit_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
    }

    static void SettingText(String t, String p, String o, String on, String op) {
        str_order_title = t;
        str_order_price = p;
        str_order_option = o;
        str_order_option_name = on;
        str_order_option_price = op;

    }
    /*
        매개변수 추가해서 디비에 있는 값 받아와서 사용할것!
     */
    public void AddOption() {
            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.order_option_layout);
            LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View menulist = inflater.inflate(R.layout.pocs_order_option , null);
            linearLayout.addView(menulist);


            TextView menutext_1 = (TextView)menulist.findViewById(R.id.option_title);
            final CheckBox menutext_2 = (CheckBox)menulist.findViewById(R.id.checkbox_1);
            TextView menutext_3 = (TextView)menulist.findViewById(R.id.option_price);

            menutext_1.setText(str_order_option_name);
            menutext_2.setText(str_order_option);
            menutext_3.setText(str_order_option_price + "원");

            menutext_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                /*
                    인트형으로 변환한후 값 변경하도록
                    스트링 값 출력은 "+" + 변수 + "원" 이런식으로 구상할 것
                */
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(menutext_2.isChecked() == true) {
                        check = true;
                        sum = Integer.parseInt(str_order_price) + Integer.parseInt(str_order_option_price);
                        order_finish_price.setText(sum + "원");
                    } else {
                        check = false;
                        order_finish_price.setText(Integer.parseInt(str_order_price) + "원");
                    }
                }
            });
    }

    public void SetOrderText(String title, String price) {
        TextView order_title = (TextView)findViewById(R.id.order_title);
        TextView order_price = (TextView)findViewById(R.id.order_price);
        order_finish_price = (TextView)findViewById(R.id.finish_order_price);

        order_title.setText(str_order_title);
        order_price.setText(str_order_price);
        order_finish_price.setText(str_order_price + "원");
    }

    public int Menu_File_Size() {
        String line = null;
        int num = 0;
        try {
            BufferedReader buf = new BufferedReader(new FileReader("/data/data/com.example.poscapp/app_raw/menudata.txt"));
            while((line=buf.readLine())!=null){
                num++;
            }
            buf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return num;
    }

    public void checkPermission(){
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    public void Menu_Write_File(String name, String price, String count) {
        String file_write = Menu_File_Size() + "@@" + name + "@@" + price + "@@" + count;
        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter("/data/data/com.example.poscapp/app_raw/menudata.txt", true));
            buf.append(file_write); // 파일 쓰기
            buf.newLine(); // 개행
            buf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
