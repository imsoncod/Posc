package com.example.poscapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Posc_Address_Activity extends AppCompatActivity {

    private WebView daum_webView;

    static TextView daum_result;

    private Handler handler;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.address_layout);

        daum_result = (TextView) findViewById(R.id.daum_result);


        // WebView 초기화
        init_webView();


        // 핸들러를 통한 JavaScript 이벤트 반응

        handler = new Handler();

    }

    public void init_webView() {

        // WebView 설정
        daum_webView = (WebView) findViewById(R.id.daum_webview);
        daum_webView.getSettings().setJavaScriptEnabled(true);
        daum_webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        daum_webView.getSettings().setSupportMultipleWindows(true);
        daum_webView.loadUrl("http://183.91.253.81:3100/WebTest/address.jsp");
        daum_webView.addJavascriptInterface(new AndroidBridge(), "PoscApp");
        daum_webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, Message resultMsg) {
                WebView newWeb = new WebView(Posc_Address_Activity.this);
                WebSettings webSettings = newWeb.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
                final Dialog dia = new Dialog(Posc_Address_Activity.this);
                dia.setContentView(newWeb);

                ViewGroup.LayoutParams params = dia.getWindow().getAttributes();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                dia.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
                dia.show();
                newWeb.setWebChromeClient(new WebChromeClient() {
                    public  void onCloseWindow(WebView window) {
                        dia.dismiss();
                    }
                });
                ((WebView.WebViewTransport) resultMsg.obj).setWebView(newWeb);
                resultMsg.sendToTarget();
                return true;
            }
        });
    }


    private class AndroidBridge {

        @JavascriptInterface

        public void setAddress(final String arg1, final String arg2, final String arg3) {

            handler.post(new Runnable() {

                @Override

                public void run() {

                    daum_result.setText(String.format("%s %s", arg2, arg3));
                    Intent intent = new Intent(getApplicationContext(), Posc_Address_Input_Activity.class);
                    startActivity(intent);
                    init_webView();

                }

            });
        }
    }
}
