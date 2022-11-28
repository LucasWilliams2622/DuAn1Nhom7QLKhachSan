package com.example.duan1nhom7qlkhachsan.Guide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.duan1nhom7qlkhachsan.R;

public class Load3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load3);

        TextView tvNext1 = findViewById(R.id.tvNext7);
        TextView tvNext2 = findViewById(R.id.tvNext8);
        tvNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenMove = new Intent(Load3Activity.this,Load4Activity.class);
                startActivity(intenMove);

            }
        });
        tvNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenMove = new Intent(Load3Activity.this,Load4Activity.class);
                startActivity(intenMove);


            }
        });
    }
}