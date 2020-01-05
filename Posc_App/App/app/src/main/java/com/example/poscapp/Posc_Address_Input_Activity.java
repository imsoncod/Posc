package com.example.poscapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Posc_Address_Input_Activity extends AppCompatActivity {
    private Intent intent;
    static  String finish_address = "내 주소검색";
    EditText text_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posc_address_input);

        TextView text = (TextView)findViewById(R.id.address_view);
        text.setText(Posc_Address_Activity.daum_result.getText());
        text_input = (EditText)findViewById(R.id.address_view_input);
        Button succ = (Button)findViewById(R.id.address_succ);
        Button canc = (Button)findViewById(R.id.address_canc);

        succ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_address = Posc_Address_Activity.daum_result.getText() + text_input.getText().toString();
                intent = new Intent(getApplicationContext(), Posc_Home_Activity.class);
                startActivity(intent);
            }
        });

        canc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), Posc_Home_Activity.class);
                startActivity(intent);
            }
        });
    }
}