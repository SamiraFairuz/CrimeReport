package com.example.On_The_Go_Crime_Report;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PoliceActivity extends AppCompatActivity {
    private Button btn1, btn2;
    TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);

        btn1 = (Button) findViewById(R.id.btnShowReport);
        btn2= (Button) findViewById(R.id.btnStatus);
        logout=(TextView) findViewById(R.id.logoutBtn);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(PoliceActivity.this,MainActivity.class);
                startActivity(I);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(PoliceActivity.this,showCrimePolice.class);
                startActivity(I);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(PoliceActivity.this,UpdateStatus.class);
                startActivity(i);
            }
        });
    }
}
