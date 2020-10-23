package com.example.On_The_Go_Crime_Report;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.On_The_Go_Crime_Report.Model.Crime;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class showCrimePolice extends AppCompatActivity {

    private RecyclerView crimeList;

    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_crime_police);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Crimes");

        crimeList = (RecyclerView) findViewById(R.id.crimeList);
        crimeList.setHasFixedSize(true);
        crimeList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Crime> options=
                new FirebaseRecyclerOptions.Builder<Crime>()
                        .setQuery(databaseReference,Crime.class)
                        .setLifecycleOwner(this)
                        .build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Crime, CrimeViewHolder>(options) {
            @Override
            public CrimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.crime_layout, parent, false);

                return new CrimeViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(CrimeViewHolder holder, int position, Crime model) {
                // Bind the Chat object to the ChatHolder
                // ...
                holder.setArea(model.getArea());
                holder.setDesc(model.getDesc());
                holder.setImage(getApplicationContext(),model.getImage());
                holder.setStatus(model.getStatus());
            }
        };

        crimeList.setAdapter(adapter);
    }

    public static class CrimeViewHolder extends RecyclerView.ViewHolder{

        View view;

        public CrimeViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
        }
        public void setArea (String area){
            TextView post_area = (TextView) view.findViewById(R.id.crimeArea);
            post_area.setText(area);
        }

        public void setDesc(String desc){
            TextView post_desc = (TextView) view.findViewById(R.id.crimeDesc);
            post_desc.setText(desc);
        }
        public void setImage(Context ctx,String image){

            ImageView post_image = (ImageView) view.findViewById(R.id.crimeImg);
            Picasso.with(ctx).load(image).resize(120,60).placeholder(R.drawable.error).error(R.drawable.error).into(post_image);


        }
        public void setStatus(String status){
            TextView post_status = (TextView) view.findViewById(R.id.crimeStatus);
            post_status.setText(status);
        }
    }
}
