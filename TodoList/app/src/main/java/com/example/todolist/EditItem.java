package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class EditItem extends AppCompatActivity {

    TextView delete, title1;
    EditText title, plan, time;
    Button btnupdate;
    ImageView btns;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        title1 = findViewById(R.id.title1);
        title = findViewById(R.id.title);
        plan = findViewById(R.id.plan);
        time = findViewById(R.id.time);
        btnupdate = findViewById(R.id.btnupdate);
        delete = findViewById(R.id.delete);
        btns = findViewById(R.id.backs);

        title1.setText(getIntent().getStringExtra("title"));
        plan.setText(getIntent().getStringExtra("plan"));
        title.setText(getIntent().getStringExtra("title"));
        time.setText(getIntent().getStringExtra("time"));

        final String keys = getIntent().getStringExtra("key");
        reference = FirebaseDatabase.getInstance().getReference().child("TodoList").child("List" + keys);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child("plan").setValue(plan.getText().toString());
                reference.child("time").setValue(time.getText().toString());
                reference.child("title").setValue(title.getText().toString());
                reference.child("keys").setValue(keys);
                Intent ab = new Intent(EditItem.this, MainActivity.class);
                startActivity(ab);
                Toast.makeText(getApplicationContext(), "Update Success", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(EditItem.this)
                        .setIcon(R.drawable.logo)
                        .setTitle("Delete Task")
                        .setMessage("Are you sure to delete this Task?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        dataSnapshot.getRef().removeValue();
                                        Toast.makeText(getApplicationContext(), "Task Deleted", Toast.LENGTH_SHORT).show();
                                        Intent a = new Intent(EditItem.this, MainActivity.class);
                                        startActivity(a);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });

        btns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pp = new Intent(EditItem.this, MainActivity.class);
                startActivity(pp);
            }
        });
    }
}
