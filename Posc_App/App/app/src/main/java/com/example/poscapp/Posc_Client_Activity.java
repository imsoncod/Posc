package com.example.poscapp;

/*콘솔 멀티채팅 클라이언트 프로그램*/
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Posc_Client_Activity {
    static Socket socket;
    public Posc_Client_Activity() {
        try{
            String ServerIP = "183.91.253.81";
            socket = new Socket(ServerIP, 9190); //소켓 객체 생성
            //사용자로부터 얻은 문자열을 서버로 전송해주는 역할을 하는 쓰레드.

            /////////////* 사용자 이름 중복체크 (테스트용)*///////////////
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            //Scanner s = new Scanner(System.in);
            while(true){
                dos.writeUTF("01097292751");
                dos.flush();
                String chk_code = dis.readUTF();
                //System.out.println("chk_code=>"+chk_code);
                if(!chk_code.equals("!errCode404")){
                    break;
                }
            }

            /////////////////////////////////////////////////////////////

            //서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 쓰레드.
            Thread receiver = new Receiver(socket);
            receiver.start(); //스레드 시동

        }catch(Exception e){
            System.out.println("예외[MultiClient class]:"+e);
        }

    }//main()-------
}//End class MultiClient


/////////////////////////////////////////////////////////////////////


//서버로부터 메시지를 읽는 클래스
class Receiver extends Thread{
    Socket socket;
    DataInputStream in;
    //Socket을 매개변수로 받는 생성자.
    public Receiver(Socket socket){
        this.socket = socket;

        try{
            in = new DataInputStream(this.socket.getInputStream());
        }catch(Exception e){
            System.out.println("예외:"+e);
        }
    }//생성자 --------------------

    @Override
    public void run(){ //run()메소드 재정의

        while(in!=null){ //입력스트림이 null이 아니면..반복
            try{

                String msg=in.readUTF();

                //서버로부터 읽어온 데이터를 콘솔에 출력

            }catch(SocketException e){
                System.out.println("예외:"+e);
                return;

            } catch(Exception e){
                System.out.println("예외:"+e);

            }
        }//while----
    }//run()------
}//class Receiver -------