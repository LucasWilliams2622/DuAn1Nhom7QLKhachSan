package com.example.duan1nhom7qlkhachsan.Guide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.duan1nhom7qlkhachsan.R;

public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        TextView tvNext1 = findViewById(R.id.tvNext3);
        TextView tvNext2 = findViewById(R.id.tvNext4);
        tvNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenMove = new Intent(LoadActivity.this,Load1Activity.class);
                startActivity(intenMove);

            }
        });
        tvNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenMove = new Intent(LoadActivity.this,Load1Activity.class);
                startActivity(intenMove);


            }
        });
    }
}