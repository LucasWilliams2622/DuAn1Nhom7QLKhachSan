package com.example.duan1nhom7qlkhachsan.Guide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.duan1nhom7qlkhachsan.R;

public class Load1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load1);

        TextView tvNext1 = findViewById(R.id.tvNext5);
        TextView tvNext2 = findViewById(R.id.tvNext6);
        tvNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenMove = new Intent(Load1Activity.this,Load3Activity.class);
                startActivity(intenMove);


            }
        });
        tvNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenMove = new Intent(Load1Activity.this,Load3Activity.class);
                startActivity(intenMove);


            }
        });
    }
}