package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView my_list;
    ImageView back;

    DatabaseReference reference;
    RecyclerView data;
    ArrayList<GetSet> list;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_list = findViewById(R.id.my_list);

        back = findViewById(R.id.backs);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(MainActivity.this, homescreen.class);
                startActivity(aa);
            }
        });

        data = findViewById(R.id.data);
        data.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<GetSet>();

        reference = FirebaseDatabase.getInstance().getReference().child("TodoList");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    GetSet g = dataSnapshot1.getValue(GetSet.class);
                    list.add(g);
                }
                adapter = new MyAdapter(MainActivity.this, list);
                data.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Tidak ada Data yang tersimpan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
