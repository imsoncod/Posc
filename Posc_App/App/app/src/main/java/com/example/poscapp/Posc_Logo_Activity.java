package com.example.poscapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Posc_Logo_Activity extends AppCompatActivity {

    TelephonyManager tMgr;

    private String[] permissions = {
            Manifest.permission.READ_PHONE_NUMBERS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private static final int MULTIPLE_PERMISSIONS = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posc_logo);
        tMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        TextView pos = (TextView) findViewById(R.id.part_1);
        TextView c = (TextView) findViewById(R.id.part_2);

        Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.loading_part1);
        pos.startAnimation(anim1);

        Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.loading_part2);
        c.startAnimation(anim2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try { // 스레드에게 수행시킬 동작들 구현
                    Thread.sleep(2000);

                    if (Build.VERSION.SDK_INT >= 23) { // 안드로이드 6.0 이상일 경우 퍼미션 체크
                        checkPermissions();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Intent i = new Intent(Posc_Logo_Activity.this, Posc_Logo_Activity.class);  //your class
        startActivity(i);
        finish();
    }

    private boolean checkPermissions() {
        int result;
        List<String> permissionList = new ArrayList<>();
        for (String pm : permissions) {
            result = ContextCompat.checkSelfPermission(this, pm);
            if (result != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(pm);
            }
        }

        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), MULTIPLE_PERMISSIONS);
            return false;
        }
        IntentActivty();
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++) {
                        if (permissions[i].equals(this.permissions[i])) {
                            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                                showToast_PermissionDeny();
                            }
                        } else {
                            IntentActivty();
                        }
                    }
                } else {
                    showToast_PermissionDeny();
                }
                onRestart();
                return;
            }
        }

    }

    private void IntentActivty() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_NUMBERS) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


            TelephonyManager telManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            String phonenum = "";
            phonenum = telManager.getLine1Number();

            if(phonenum.startsWith("+82")) // 국제번호(+82 10...)로 되어 있을경우 010 으로 변환

            {

                Posc_Home_Activity.PhoneNum = phonenum.replace("+82", "0");

            }

            Posc_Client_Activity client = new Posc_Client_Activity();
            Intent intent = new Intent(getApplicationContext(), Posc_Home_Activity.class);
            startActivity(intent);
            finish();
        }
    }

    private void showToast_PermissionDeny() {
        Toast.makeText(this, "권한 요청에 동의 해주셔야 이용 가능합니다. 설정에서 권한 허용 하시기 바랍니다.", Toast.LENGTH_SHORT).show();
        finish();
    }

}
