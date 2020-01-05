package com.example.poscapp;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Posc_Sit_Activity extends AppCompatActivity {

    private WebView sit_view;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.posc_sit_check);

        init_webView_1();

        Button b1 = (Button)findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_webView_1();
            }
        });

        Button b2 = (Button)findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_webView_2();
            }
        });

        Button b3 = (Button)findViewById(R.id.sit_canc);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void init_webView_1() {

        // WebView 설정
        sit_view = (WebView) findViewById(R.id.sit_view);
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
        sit_view = (WebView) findViewById(R.id.sit_view);
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
}
