package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class AddItem extends AppCompatActivity {

    TextView list, planlist, timelist;
    EditText title, plan, time;
    ImageView close;
    Button create, add;
    DatabaseReference reference;
    Integer num = new Random().nextInt();
    String keys = Integer.toString(num);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        //Textview
        list = findViewById(R.id.list);
        planlist = findViewById(R.id.planlist);
        timelist = findViewById(R.id.timelist);

        //Edittext
        title = findViewById(R.id.title);
        plan = findViewById(R.id.plan);
        time = findViewById(R.id.time);

        //Button
        close = findViewById(R.id.close);
        create = findViewById(R.id.create);
        add = findViewById(R.id.add);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference().child("TodoList").child("List" + num);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("title").setValue(title.getText().toString());
                        dataSnapshot.getRef().child("plan").setValue(plan.getText().toString());
                        dataSnapshot.getRef().child("time").setValue(time.getText().toString());
                        dataSnapshot.getRef().child("key").setValue(keys);

                        Intent move = new Intent(AddItem.this, MainActivity.class);
                        startActivity(move);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent close = new Intent(AddItem.this, homescreen.class);
                startActivity(close);
            }
        });
    }
}
