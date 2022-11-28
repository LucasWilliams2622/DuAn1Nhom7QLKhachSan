package com.example.duan1nhom7qlkhachsan.Guide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.duan1nhom7qlkhachsan.R;

public class Load2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load2);

        TextView tvNext1 = findViewById(R.id.tvNext1);
        TextView tvNext2 = findViewById(R.id.tvNext2);
        tvNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenMove = new Intent(Load2Activity.this,LoadActivity.class);
               startActivity(intenMove);

            }
        });
        tvNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenMove = new Intent(Load2Activity.this,LoadActivity.class);
                startActivity(intenMove);

            }
        });
    }
}