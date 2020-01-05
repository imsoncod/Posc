package com.example.poscapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Posc_Menu_Activity extends AppCompatActivity {
    private Intent intent;
    private PopupWindow pwindo;
    static TextView menu_count;
    static String str_menutitle, str_menuphone;
    static int num, mWidthPixels, mHeightPixels;
    private WebView sit_view;
    View layout;
    public String htmlUrl = "http://192.168.0.2:3100/WebTest/Oracle2.jsp";
    String htmlInString = "";
    ArrayList data = new ArrayList();
    ArrayList data1 = new ArrayList();
    ArrayList data2 = new ArrayList();
    ArrayList data3 = new ArrayList();
    ArrayList data4 = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posc_menu);
        checkPermission();
        WindowManager w = getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        // since SDK_INT = 1;
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;
        // 상태바와 메뉴바의 크기를 포함해서 재계산
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
            try {
                mWidthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                mHeightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored) {
            }
        // 상태바와 메뉴바의 크기를 포함
        if (Build.VERSION.SDK_INT >= 17)
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                mWidthPixels = realSize.x;
                mHeightPixels = realSize.y;
            } catch (Exception ignored) {

            }

        Button sitcheck = (Button)findViewById(R.id.sit_check);
        sitcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  LayoutInflater 객체와 시킴
                LayoutInflater inflater = (LayoutInflater) Posc_Menu_Activity.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                layout = inflater.inflate(R.layout.posc_sit_check,
                        (ViewGroup) findViewById(R.id.sit_layout));

                sit_view = (WebView)layout.findViewById(R.id.sit_view);

                pwindo = new PopupWindow(layout, mWidthPixels-100, mHeightPixels-380, true);
                pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

                init_webView_1();

                Button b1 = (Button)layout.findViewById(R.id.b1);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        init_webView_1();
                    }
                });

                Button b2 = (Button)layout.findViewById(R.id.b2);
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        init_webView_2();
                    }
                });

                Button b3 = (Button)layout.findViewById(R.id.sit_canc);
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pwindo.dismiss();
                    }
                });
            }
        });

        Button menuorders = (Button)findViewById(R.id.menuorders);
        menuorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), Posc_Menu_Finish_Order_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.posc_page_right, R.anim.posc_page_hold);
            }
        });

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
                    case R.id.menu_search:
                        intent = new Intent(getApplicationContext(), Posc_Search_Activity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.posc_page_right, R.anim.posc_page_hold);
                        return true;
                }
                return false;
            }
        });
        MenuSetValues(str_menutitle, str_menuphone, num);
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();

        menu_count = (TextView)findViewById(R.id.menu_count);
        //.makeText(getApplicationContext(), Posc_Menu_Finish_Order_Activity.menu_size+"", Toast.LENGTH_LONG).show();
        if(Posc_Menu_Finish_Order_Activity.menu_size != 0) {
            menu_count.setVisibility(View.VISIBLE);
            menu_count.setText(Posc_Menu_Finish_Order_Activity.menu_size+"");
        }
    }

    public void checkPermission(){
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                Document doc = Jsoup.connect(htmlUrl).get();

                Elements item1 = doc.select("a");
                Elements item2 = doc.select("a1");
                Elements item3 = doc.select("a2");
                Elements item4 = doc.select("a3");
                Elements item5 = doc.select("a4");

                for (Element e : item1) {
                    data.add(e.text().toString());
                }

                for (Element e : item2) {
                    data1.add(e.text().toString());
                }

                for (Element e : item3) {
                    data2.add(e.text().toString());
                }

                for (Element e : item4) {
                    data3.add(e.text().toString());
                }

                for (Element e : item5) {
                    data4.add(e.text().toString());
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            for(int i =0; i < data.size(); i++) {
                TableCreate(data.get(i).toString(), data2.get(i).toString(), data1.get(i).toString(), i);
            }
        }
    }

    static void MenuGetVaules(String title, String phone, int n) {
        str_menutitle = title;
        str_menuphone = phone;
        num = n;
    }

    public void init_webView_1() {

        // WebView 설정
        sit_view.getSettings().setJavaScriptEnabled(true);
        sit_view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        sit_view.getSettings().setSupportMultipleWindows(true);
        sit_view.loadUrl("http://192.168.0.5:8087/Map/TableMap1.jsp");
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

    public void init_webView_2() {

        // WebView 설정
        sit_view.getSettings().setJavaScriptEnabled(true);
        sit_view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        sit_view.getSettings().setSupportMultipleWindows(true);
        sit_view.loadUrl("http://192.168.0.5:8087/Map/TableMap2.jsp");
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

    TextView menutext_1, menutext_2, menutext_3;

    public void TableCreate(String a, String b, String c, int i) {

        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.menulayout);
        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View menulist[] = new View[data.size()];
        menulist[i] = inflater.inflate(R.layout.posc_menu_table_input , null);

        menutext_1 = (TextView)menulist[i].findViewById(R.id.menu_table_input_text1);
        menutext_2 = (TextView)menulist[i].findViewById(R.id.menu_table_input_text2);
        menutext_3 = (TextView)menulist[i].findViewById(R.id.menu_table_input_text3);

        menutext_1.setText(a);
        menutext_2.setText(b);
        menutext_3.setText(c + "원");

        linearLayout.addView(menulist[i]);
        menulist[i].setTag(i);
        menulist[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = 0;
                if(v.getTag().toString().equals("0")) {
                    size = 0;
                    menutext_1 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text1);
                    menutext_2 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text2);
                    menutext_3 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text3);
                    Posc_Menu_Order_Activity.SettingText(menutext_1.getText().toString(), data1.get(size).toString(),menutext_2.getText().toString(), data3.get(size).toString(), data4.get(size).toString());
                    intent = new Intent(getApplicationContext(), Posc_Menu_Order_Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.posc_page_right, R.anim.posc_page_hold);
                    finish();
                }
                else if(v.getTag().toString().equals("1")) {
                    size = 1;
                    menutext_1 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text1);
                    menutext_2 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text2);
                    menutext_3 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text3);
                    Posc_Menu_Order_Activity.SettingText(menutext_1.getText().toString(), data1.get(size).toString(),menutext_2.getText().toString(), data3.get(size).toString(), data4.get(size).toString());
                    intent = new Intent(getApplicationContext(), Posc_Menu_Order_Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.posc_page_right, R.anim.posc_page_hold);
                    finish();
                }
                else if(v.getTag().toString().equals("2")) {
                    size = 2;
                    menutext_1 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text1);
                    menutext_2 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text2);
                    menutext_3 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text3);
                    Posc_Menu_Order_Activity.SettingText(menutext_1.getText().toString(), data1.get(size).toString(),menutext_2.getText().toString(), data3.get(size).toString(), data4.get(size).toString());
                    intent = new Intent(getApplicationContext(), Posc_Menu_Order_Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.posc_page_right, R.anim.posc_page_hold);
                    finish();
                }
                else if(v.getTag().toString().equals("3")) {
                    size = 3;
                    menutext_1 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text1);
                    menutext_2 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text2);
                    menutext_3 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text3);
                    Posc_Menu_Order_Activity.SettingText(menutext_1.getText().toString(), data1.get(size).toString(),menutext_2.getText().toString(), data3.get(size).toString(), data4.get(size).toString());
                    intent = new Intent(getApplicationContext(), Posc_Menu_Order_Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.posc_page_right, R.anim.posc_page_hold);
                    finish();
                }
                else if(v.getTag().toString().equals("4")) {
                    size = 4;
                    menutext_1 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text1);
                    menutext_2 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text2);
                    menutext_3 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text3);
                    Posc_Menu_Order_Activity.SettingText(menutext_1.getText().toString(), data1.get(size).toString(),menutext_2.getText().toString(), data3.get(size).toString(), data4.get(size).toString());
                    intent = new Intent(getApplicationContext(), Posc_Menu_Order_Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.posc_page_right, R.anim.posc_page_hold);
                    finish();
                }
                else if(v.getTag().toString().equals("5")) {
                    size = 5;
                    menutext_1 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text1);
                    menutext_2 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text2);
                    menutext_3 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text3);
                    Posc_Menu_Order_Activity.SettingText(menutext_1.getText().toString(), data1.get(size).toString(),menutext_2.getText().toString(), data3.get(size).toString(), data4.get(size).toString());
                    intent = new Intent(getApplicationContext(), Posc_Menu_Order_Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.posc_page_right, R.anim.posc_page_hold);
                    finish();
                }
                else if(v.getTag().toString().equals("6")) {
                    size = 6;
                    menutext_1 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text1);
                    menutext_2 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text2);
                    menutext_3 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text3);
                    Posc_Menu_Order_Activity.SettingText(menutext_1.getText().toString(), data1.get(size).toString(),menutext_2.getText().toString(), data3.get(size).toString(), data4.get(size).toString());
                    intent = new Intent(getApplicationContext(), Posc_Menu_Order_Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.posc_page_right, R.anim.posc_page_hold);
                    finish();
                }
                else if(v.getTag().toString().equals("7")) {
                    size = 7;
                    menutext_1 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text1);
                    menutext_2 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text2);
                    menutext_3 = (TextView)menulist[size].findViewById(R.id.menu_table_input_text3);
                    Posc_Menu_Order_Activity.SettingText(menutext_1.getText().toString(), data1.get(size).toString(),menutext_2.getText().toString(), data3.get(size).toString(), data4.get(size).toString());
                    intent = new Intent(getApplicationContext(), Posc_Menu_Order_Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.posc_page_right, R.anim.posc_page_hold);
                    finish();
                }
            }
        });
    }

    public void MenuSetValues(String title, String phone, int n) {
        ImageView menuicon = (ImageView)findViewById(R.id.menuicon);
        TextView menutitle = (TextView)findViewById(R.id.menutitle);
        TextView menutext = (TextView)findViewById(R.id.menutext);
        TextView menutphone = (TextView)findViewById(R.id.menuphone);

        switch (n) {
            case 1:
                menuicon.setImageResource(R.drawable.jfood);
                menutitle.setText(title);
                menutext.setText(title);
                menutphone.setText(phone);
                break;
            case 2:
                menuicon.setImageResource(R.drawable.jfood);
                menutitle.setText(title);
                menutext.setText(title);
                menutphone.setText(phone);
                break;
        }

    }
}
