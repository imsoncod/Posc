package com.example.poscapp;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class Posc_Menu_Finish_Order_Activity extends AppCompatActivity {

    public Posc_Menu_Finish_Order_Activity() {  }
    private Intent intent;
    // 여기서 사용하는 값
    static String str_order_finish_name = "null";

    // 그 메뉴의 총액
    static int str_order_sum_price;
    int order_price[] = new int[Menu_File_Size()];
    int use_order_price[] = new int[Menu_File_Size()];
    String menu_price_origin[] = new String[Menu_File_Size()];
    static int finish_price = 0;
    public View[] menulist = new View[Menu_File_Size()];
    TextView menu_name[] = new TextView[Menu_File_Size()];
    TextView menu_num[] = new TextView[Menu_File_Size()];
    TextView menu_price[] = new TextView[Menu_File_Size()];
    TextView menu_finish_sum_price;
    int mWidthPixels, mHeightPixels;
    private PopupWindow pwindo;
    View layout;
    private WebView sit_view;
    static int menu_size = 0;
    int menu_i = 0;
    int sit_counting = 1;
    static String message = "추가 사항 없습니다.";
    static String Mene_Order_Text = "";
    TextView sit_count;
    static String str_sit_count = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_finish_order_table);
        menu_finish_sum_price = (TextView)findViewById(R.id.order_finish_sum_price);
        menu_finish_sum_price.setText((str_order_sum_price+2000)+"원");
        WindowManager w = getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        // since SDK_INT = 1;
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;
        checkPermission();
        Menu_File_Read();

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

        Button del = (Button)findViewById(R.id.order_finish_del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다! 배달 주문 되었습니다!", Toast.LENGTH_LONG).show();
                DirSendMsg();
            }
        });

        Button message_btn = (Button)findViewById(R.id.message_btn);
        message_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) Posc_Menu_Finish_Order_Activity.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                layout = inflater.inflate(R.layout.posc_message, (ViewGroup) findViewById(R.id.message_layout));

                pwindo = new PopupWindow(layout, mWidthPixels-100, mHeightPixels-1350, true);
                pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

                final EditText message_text = (EditText)layout.findViewById(R.id.message_text);

                Button message_ok = (Button)layout.findViewById(R.id.message_ok);
                message_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        message = message_text.getText().toString();
                        pwindo.dismiss();
                    }
                });

                Button message_no = (Button)layout.findViewById(R.id.message_no);
                message_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pwindo.dismiss();
                    }
                });
            }
        });

        Button reg = (Button)findViewById(R.id.order_finish_reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  LayoutInflater 객체와 시킴
                String txt = "";
                System.out.println("값 : " + menulist.length);
                for(int i = 0; i < menulist.length; i++) {
                    txt += menu_name[i].getText().toString() + "!" + menu_num[i].getText().toString()+ "!" + menu_price_origin[i]+"#";
                }
                Mene_Order_Text = txt.substring(0, txt.length()-1);
                System.out.println(Mene_Order_Text);

                LayoutInflater inflater = (LayoutInflater) Posc_Menu_Finish_Order_Activity.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                layout = inflater.inflate(R.layout.posc_sit_select, (ViewGroup) findViewById(R.id.select_layout));

                sit_view = (WebView)layout.findViewById(R.id.sit_view);

                pwindo = new PopupWindow(layout, mWidthPixels-100, mHeightPixels-400, true);
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

                Button b3 = (Button)layout.findViewById(R.id.btn_select_reg);
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RegSendMsg();
                        Toast.makeText(getApplicationContext(), "감사합니다! 예약되었습니다!", Toast.LENGTH_LONG).show();
                        pwindo.dismiss();
                    }
                });

                Button b4 = (Button)layout.findViewById(R.id.btn_select_cancle);
                b4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pwindo.dismiss();
                    }
                });

                sit_count = (TextView)layout.findViewById(R.id.option_finish_total);

                Button add = (Button)layout.findViewById(R.id.option_finish_add);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sit_counting++;
                        sit_count.setText(sit_counting+"");
                        str_sit_count = sit_count.getText().toString();
                    }
                });

                Button sub = (Button)layout.findViewById(R.id.option_finish_sub);
                sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(sit_counting > 1) {
                            sit_counting--;
                            sit_count.setText(sit_counting+"");
                            str_sit_count = sit_count.getText().toString();
                        }
                    }
                });
            }
        });


        Button canc = (Button)findViewById(R.id.order_finish_cancle);
        canc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getApplicationContext(), menu_size+"", Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext(), Posc_Menu_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.posc_page_right, R.anim.posc_page_hold);
                finish();
            }
        });

    }
    /*
        매개변수 추가해서 디비에 있는 값 받아와서 사용할것!
     */
    public void AddOption(String n, String p, int i) {
        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.order_finish_option_layout);
        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        menu_i = i;
        menulist[i] = inflater.inflate(R.layout.pocs_finish_order_option , null);
        linearLayout.addView(menulist[i]);

        menu_name[i] = (TextView)menulist[i].findViewById(R.id.option_finish_title);
        menu_num[i] = (TextView)menulist[i].findViewById(R.id.option_finish_total);
        menu_price[i] = (TextView)menulist[i].findViewById(R.id.option_finish_price);
        menu_price_origin[i] = p+"";
        menu_name[i].setText(n);
        menu_price[i].setText("+"+p+"원");
        str_order_sum_price = Integer.parseInt(p);
        order_price[i] = str_order_sum_price;
        menu_num[i].setText("1");

        finish_price = 0;
        for(int j = 0; j < menulist.length; j++) {
            use_order_price[j] = order_price[j];
            finish_price += order_price[j];
        }
        menu_finish_sum_price.setText((finish_price+2000)+"원");

        final Button[] menu_del = new Button[Menu_File_Size()];
        menu_del[i] = (Button)menulist[i].findViewById(R.id.option_finish_remove);
        menu_del[i].setTag(i);
        menu_del[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeView(menulist[Integer.parseInt(v.getTag()+"")]);
                menu_name[Integer.parseInt(v.getTag()+"")].setText("null");
                menu_num[Integer.parseInt(v.getTag()+"")].setText("null");
                menu_price[Integer.parseInt(v.getTag()+"")].setText("null");
                DeleteFile();
               // Toast.makeText(getApplicationContext(), menulist.length+"", Toast.LENGTH_LONG).show();
                for(int i = 0; i < menulist.length; i++) {
                    if(!menu_name[i].getText().toString().equals("null") && !menu_num[i].getText().toString().equals("null") && !menu_price[i].getText().toString().equals("null")) {
                        Menu_Write_File(menu_name[i].getText().toString(), str_order_sum_price + "", menu_num[i].getText().toString());
                    }
                }
            }
        });
        Button menu_add[] = new Button[Menu_File_Size()];
        menu_add[i] = (Button)menulist[i].findViewById(R.id.option_finish_add);
        menu_add[i].setTag(i);
        menu_add[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = Integer.parseInt(menu_num[Integer.parseInt(v.getTag()+"")].getText().toString())+1;

                int price[] = new int[Menu_File_Size()];
                price[Integer.parseInt(v.getTag()+"")] = use_order_price[Integer.parseInt(v.getTag()+"")] * sum;
                order_price[Integer.parseInt(v.getTag()+"")] = price[Integer.parseInt(v.getTag()+"")];

                menu_num[Integer.parseInt(v.getTag()+"")].setText(sum+"");
                menu_price[Integer.parseInt(v.getTag()+"")].setText("+"+order_price[Integer.parseInt(v.getTag()+"")]+"원");
                finish_price = 0;
                for(int i = 0; i < menulist.length; i++) {
                    finish_price += order_price[i];
                }

                Toast.makeText(getApplicationContext(), "크기 : " + menulist.length + "값 : " + finish_price+"", Toast.LENGTH_LONG).show();
                menu_finish_sum_price.setText((finish_price+2000)+"원");
            }
        });

        Button menu_sub[] = new Button[Menu_File_Size()];
        menu_sub[i] = (Button)menulist[i].findViewById(R.id.option_finish_sub);
        menu_sub[i].setTag(i);
        menu_sub[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), v.getTag()+"", Toast.LENGTH_LONG).show();
                int sub = Integer.parseInt(menu_num[Integer.parseInt(v.getTag()+"")].getText().toString());
                if(sub == 1) {
                    menu_num[Integer.parseInt(v.getTag()+"")].setText("1");
                } else {
                    sub--;
                    int price[] = new int[Menu_File_Size()];
                    price[Integer.parseInt(v.getTag()+"")] = 0;
                    price[Integer.parseInt(v.getTag()+"")] = order_price[Integer.parseInt(v.getTag()+"")] - use_order_price[Integer.parseInt(v.getTag()+"")];
                    order_price[Integer.parseInt(v.getTag()+"")] = price[Integer.parseInt(v.getTag()+"")];

                    menu_num[Integer.parseInt(v.getTag()+"")].setText(sub+"");
                    menu_price[Integer.parseInt(v.getTag()+"")].setText("+"+order_price[Integer.parseInt(v.getTag()+"")]+"원");
                    finish_price = 0;
                    for(int i = 0; i < menulist.length; i++) {
                        finish_price += order_price[i];
                    }

                    //Toast.makeText(getApplicationContext(), "크기 : " + menulist.length + "값 : " + finish_price+"", Toast.LENGTH_LONG).show();
                    menu_finish_sum_price.setText((finish_price+2000)+"원");
                }
            }
        });
    }

    static public void GetValues(String n, int s) {
        str_order_finish_name = n;
        str_order_sum_price = s;
    }

    @Override
    public void onBackPressed() {
        menu_size = menulist.length;
        //Toast.makeText(getApplicationContext(), menu_size+"", Toast.LENGTH_LONG).show();
        intent = new Intent(getApplicationContext(), Posc_Menu_Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.posc_page_right, R.anim.posc_page_hold);
        finish();
        super.onBackPressed();
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

    public void DeleteFile() {
        File file = new File("/data/data/com.example.poscapp/app_raw/menudata.txt");
        if (file.exists()) {
            if (file.delete()) {
                //Toast.makeText(getApplicationContext(), "삭제완료", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(getApplicationContext(), "삭제실패", Toast.LENGTH_LONG).show();
            }
        } else {
           // Toast.makeText(getApplicationContext(), "파일없는데?", Toast.LENGTH_LONG).show();
        }
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

    public void checkPermission(){
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    public int Menu_File_Read() {
        String line = null;
        String[] word = null;
        int i = 0;
        int num = 0;
        try {
            BufferedReader buf = new BufferedReader(new FileReader("/data/data/com.example.poscapp/app_raw/menudata.txt"));
            while((line=buf.readLine())!=null){
                word = line.split("@@");
                AddOption(word[1], word[2], i);
                menulist[i].setTag(i);
                i++;
            }
            buf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return num;
    }

    static void RegSendMsg() {
        Thread sender = new Sender(Posc_Client_Activity.socket, "01097292751",
                Posc_Select_Activity.storenum+"@@"+str_sit_count+"@@"+"01097292751"+"@@"+Mene_Order_Text+"@@"+ (finish_price+2000) +"@@"+message+"@@r");
        sender.start(); //스레드 시동
    }
    static void DirSendMsg() {
        Thread sender = new Sender(Posc_Client_Activity.socket, "01097292751",
                Posc_Select_Activity.storenum + "@@" + Posc_Address_Input_Activity.finish_address + "@@" + "01097292751" + "@@" + Mene_Order_Text + "@@" + (finish_price + 2000) + "@@" + message + "@@d");
        sender.start(); //스레드 시동
    }
}

//서버로 메시지를 전송하는 클래스
class Sender extends Thread {
    Socket socket;
    String msg;
    DataOutputStream out;
    String name;

    //생성자 ( 매개변수로 소켓과 사용자 이름 받습니다. )
    public Sender(Socket socket, String name, String msg) { //소켓과 사용자 이름을 받는다.
        this.socket = socket;
        this.msg = msg;
        try {
            out = new DataOutputStream(this.socket.getOutputStream());
            this.name = name; //받아온 사용자이름을 전역변수에 저장, 다른 메서드인 run()에서 사용하기위함.
        } catch (Exception e) {
            System.out.println("예외:" + e);
        }
    }

    @Override
    public void run() { //run()메소드 재정의

        // Scanner s = new Scanner(System.in);
        //키보드로부터 입력을 받기위한 스캐너 객체 생성

//      서버에 입력한 사용자이름을 보내준다.
//      try {
//          out.writeUTF(name);
//      } catch (IOException e) {
//          System.out.println("예외:"+e);
//      }
        try { //while문 안에 try-catch문을 사용한 이유는 while문 내부에서 예외가 발생하더라도
            //계속 반복할수있게 하기위해서이다.

            //명령어 기능 추가. ( /접속자 , /귓속말 상대방아이디 전달할메시지 )
            if (msg.trim().startsWith("/")) {

                //클라이언트단에서 체크
                if (msg.equals("/접속자") || msg.startsWith("/귓속말")) {
                    out.writeUTF(msg);
                } else {
                    System.out.println("잘못된 명령어입니다.");
                    //out.writeUTF("잘못된 명령어입니다.");
                }

                //out.writeUTF(msg); //명령어 서버로 보냄.

            } else {//명령어가 아니면 키보드로부터 입력받은 문자열을 서버로 보낸다.
                out.writeUTF(msg);
            }

        } catch (SocketException e) {
            System.out.println("예외:" + e);
            System.out.println("접속중인 서버와 연결이 끊어졌습니다.");
            return;
        } catch (IOException e) {
            System.out.println("예외:" + e);
        }

    }//run()------
}//class Sender-------