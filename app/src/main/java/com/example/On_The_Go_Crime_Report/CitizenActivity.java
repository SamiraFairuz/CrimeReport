package com.example.On_The_Go_Crime_Report;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CitizenActivity extends AppCompatActivity {

    private Button btn1, btn2;
    TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen);

        btn1 = (Button) findViewById(R.id.reportCrimes);
        btn2= (Button) findViewById(R.id.showCrimes);
        logout=(TextView) findViewById(R.id.logoutBtn);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(CitizenActivity.this,MainActivity.class);
                startActivity(I);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(CitizenActivity.this,ReportCrime.class);
                startActivity(I);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(CitizenActivity.this,showCrimes.class);
                startActivity(i);
            }
        });
    }
}
