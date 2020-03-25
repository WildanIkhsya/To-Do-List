package com.example.todo_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class add extends AppCompatActivity {
    private ImageView imageview2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        imageview2 = (ImageView) findViewById(R.id.imageView2);
        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdd();
            }
        });
    }

    public void openAdd(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}