package com.example.On_The_Go_Crime_Report;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.On_The_Go_Crime_Report.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public EditText loginEmailId, loginpasswd;
    String userType;
    Button btnLogIn;
    TextView signup;
    FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner mySpinner= (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapter= new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        loginEmailId = findViewById(R.id.email);
        loginpasswd= findViewById(R.id.password);
        btnLogIn= findViewById(R.id.loginBtn);
        signup= findViewById(R.id.TV2);


        final FirebaseDatabase database= FirebaseDatabase.getInstance();
        mDatabase = database.getReference("User");

        final String c="Citizen";
        final String a="Admin";
        final String p="Police";


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(I);
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userEmail= loginEmailId.getText().toString();
                final String userPass= loginpasswd.getText().toString();
                userType= mySpinner.getSelectedItem().toString();
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(userEmail).exists()){

                        User user= dataSnapshot.child(userEmail).getValue(User.class);
                        if(user.getPassword().equals(userPass) && user.getUserType().equals(userType)){
                            Toast.makeText(MainActivity.this,"Sign in successfully!",Toast.LENGTH_SHORT).show();
                            if(user.getUserType().equals(c))
                            {
                                Intent i= new Intent(MainActivity.this,CitizenActivity.class);
                                startActivity(i);
                            }
                            else if(user.getUserType().equals(a))
                            {
                                Intent i= new Intent(MainActivity.this,AdminActivity.class);
                                startActivity(i);
                            }
                            else if(user.getUserType().equals(p))
                            {
                                Intent i= new Intent(MainActivity.this,PoliceActivity.class);
                                startActivity(i);
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Sign in failed",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                            Toast.makeText(MainActivity.this,"User does not exist",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


    }


}
