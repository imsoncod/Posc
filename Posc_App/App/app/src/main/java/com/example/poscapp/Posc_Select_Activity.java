package com.example.poscapp;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Posc_Select_Activity extends TabActivity implements TabHost.OnTabChangeListener {
    private
    Intent intent;
    TabHost tabHost;
    LinearLayout linearLayout;
    public String htmlUrl = "http://183.91.253.81:3100/WebTest/Oracle.jsp";
    String htmlInString = "";
    ArrayList data = new ArrayList();
    ArrayList data1 = new ArrayList();
    ArrayList data2 = new ArrayList();
    ArrayList data3 = new ArrayList();

    static String storenum = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posc_select);

        tabHost = getTabHost();

        TabSpec tabSpecTab0 = tabHost.newTabSpec("TAB0").setIndicator("");
        tabSpecTab0.setContent(R.id.tab1);
        tabHost.addTab(tabSpecTab0);
        tabHost.getTabWidget().getChildTabViewAt(0).setVisibility(View.GONE);

        TabSpec tabSpecTab1 = tabHost.newTabSpec("TAB1").setIndicator("한식");
        tabSpecTab1.setContent(R.id.tab1);
        tabHost.addTab(tabSpecTab1);

        TabSpec tabSpecTab2 = tabHost.newTabSpec("TAB2").setIndicator("중식");
        tabSpecTab2.setContent(R.id.tab1);
        tabHost.addTab(tabSpecTab2);

        TabSpec tabSpecTab3 = tabHost.newTabSpec("TAB3").setIndicator("양식");
        tabSpecTab3.setContent(R.id.tab1);
        tabHost.addTab(tabSpecTab3);

        TabSpec tabSpecTab4 = tabHost.newTabSpec("TAB4").setIndicator("치킨");
        tabSpecTab4.setContent(R.id.tab1);
        tabHost.addTab(tabSpecTab4);

        TabSpec tabSpecTab5 = tabHost.newTabSpec("TAB5").setIndicator("분식");
        tabSpecTab5.setContent(R.id.tab1);
        tabHost.addTab(tabSpecTab5);

        TabSpec tabSpecTab6 = tabHost.newTabSpec("TAB6").setIndicator("야식");
        tabSpecTab6.setContent(R.id.tab1);
        tabHost.addTab(tabSpecTab6);

        TabSpec tabSpecTab7 = tabHost.newTabSpec("TAB7").setIndicator("족발");
        tabSpecTab7.setContent(R.id.tab1);
        tabHost.addTab(tabSpecTab7);

        TabSpec tabSpecTab8 = tabHost.newTabSpec("TAB8").setIndicator("일식");
        tabSpecTab8.setContent(R.id.tab1);
        tabHost.addTab(tabSpecTab8);

        tabHost.setCurrentTab(Posc_Home_Activity.menu_num);

        tabHost.setOnTabChangedListener(this);

        switch (Posc_Home_Activity.menu_num) {
            case 1:
            case 2:
                JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
                jsoupAsyncTask.execute();
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
        }

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
                    case R.id.menu_search:
                        startActivity(intent);
                        overridePendingTransition(R.anim.posc_page_left, R.anim.posc_page_hold);
                        return true;
                }

                return false;
            }
        });
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

                for (Element e : item1) {
                    data.add(e.text().toString());
                }

                for (Element e : item2) {
                    htmlInString += e.text().trim() + " ";
                    data1.add(e.text().toString());
                }

                for (Element e : item3) {
                    data2.add(e.text().toString());
                }

                for (Element e : item4) {
                    data3.add(e.text().toString());
                }
                storenum = data3.get(1).toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        linearLayout = (LinearLayout) findViewById(R.id.tab1);
        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View menulist = inflater.inflate(R.layout.posc_menu_table, null);
            linearLayout.addView(menulist);


        ImageView img = (ImageView) menulist.findViewById(R.id.menu_table_img);
        TextView title = (TextView) menulist.findViewById(R.id.menu_table_title);
        TextView text = (TextView) menulist.findViewById(R.id.menu_table_text);
            img.setImageResource(0);
            title.setText(data.get(0).toString());
            text.setText(htmlInString);

            switch (Posc_Home_Activity.menu_num) {
            case 1:
                img.setImageResource(R.drawable.jfood);
                break;
            case 2:
                img.setImageResource(R.drawable.jfood);
                break;
        }

        TableLayout tableLayout = (TableLayout) menulist.findViewById(R.id.menu_table);
            tableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), Posc_Menu_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.posc_page_left, R.anim.posc_page_hold);

                /*
                메뉴 테이블에서 메뉴 눌렀을때 나오는 테이블
                디비 연동해서 사용할 것
                아래 함수 사용
                 */

                Posc_Menu_Activity.MenuGetVaules(data.get(0).toString(), data2.get(0).toString(), 1);
            }
        });


    }
    }

    @Override
    public void onTabChanged(String tabId) {
        linearLayout = (LinearLayout) findViewById(R.id.tab1);

        if (tabId.equals("TAB1")) {
            linearLayout.removeAllViews();
        }
        if (tabId.equals("TAB2")) {
            JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
            jsoupAsyncTask.execute();
            linearLayout.removeAllViews();
        }
        if (tabId.equals("TAB3")) {
            linearLayout.removeAllViews();
        }
        if (tabId.equals("TAB4")) {
            linearLayout.removeAllViews();
        }
        if (tabId.equals("TAB5")) {
            linearLayout.removeAllViews();
        }
        if (tabId.equals("TAB6")) {
            linearLayout.removeAllViews();
        }
        if (tabId.equals("TAB7")) {
            linearLayout.removeAllViews();
        }
        if (tabId.equals("TAB8")) {
            linearLayout.removeAllViews();
        }
    }
}
