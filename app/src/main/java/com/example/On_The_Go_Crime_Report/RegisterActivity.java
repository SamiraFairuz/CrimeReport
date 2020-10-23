package com.example.On_The_Go_Crime_Report;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.On_The_Go_Crime_Report.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    public EditText emailId, passwd, name, address, nid, phone, rePasswd;
    Button btnSignUp;
    TextView signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Spinner mySpinner= (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter= new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        emailId = findViewById(R.id.email1);
        name = findViewById(R.id.name1);
        address = findViewById(R.id.address1);
        nid= findViewById(R.id.NID1);
        phone= findViewById(R.id.phone1);
        rePasswd= findViewById(R.id.rePassword1);
        passwd = findViewById(R.id.password1);
        btnSignUp = findViewById(R.id.registerBtn);
        signIn= findViewById(R.id.loginTV);

        final FirebaseDatabase database= FirebaseDatabase.getInstance();
        final DatabaseReference mDatabase = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email= emailId.getText().toString();
                final String pass= passwd.getText().toString();
                final String userType= mySpinner.getSelectedItem().toString();


                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(name.getText().toString()).exists()){
                            Toast.makeText(RegisterActivity.this,"Name already exists",Toast.LENGTH_SHORT).show();
                        }
                        else if(!passwd.getText().toString().equals(rePasswd.getText().toString()))
                        {
                            Toast.makeText(RegisterActivity.this,"Passwords don't match",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            User user =new User(pass, address.getText().toString(), email, nid.getText().toString(), phone.getText().toString(),userType);
                            mDatabase.child(name.getText().toString()).setValue(user);
                            Toast.makeText(RegisterActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                            if(userType.equals("Citizen"))
                            {
                                Intent i= new Intent(RegisterActivity.this,CitizenActivity.class);
                                startActivity(i);
                            }
                            else if(userType.equals("Police"))
                            {
                                Intent i= new Intent(RegisterActivity.this,PoliceActivity.class);
                                startActivity(i);
                            }
                            else if(userType.equals("Admin"))
                            {
                                Intent i= new Intent(RegisterActivity.this,AdminActivity.class);
                                startActivity(i);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(I);
            }
        });



    }
}
