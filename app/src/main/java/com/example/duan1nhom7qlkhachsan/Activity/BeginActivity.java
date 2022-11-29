package com.example.duan1nhom7qlkhachsan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import com.example.duan1nhom7qlkhachsan.Guide.Load2Activity;
import com.example.duan1nhom7qlkhachsan.R;
import com.example.duan1nhom7qlkhachsan.SharedPreferances.DataLocalManager;


public class BeginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        ImageView ivLogo = findViewById(R.id.ivLogo);
        if( !DataLocalManager.getFirstInstall())//false first time
        {
            DataLocalManager.setFisrtInstalled(true);
            Log.d(">>>>>>>>>>>>>>>","First time");
            Glide.with(this).load(R.drawable.logo).into(ivLogo);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(BeginActivity.this, Load2Activity.class);
                    startActivity(i);
                }
            },1500);
        }
        else
        {
            Log.d(">>>>>>>>>>>>>>>","Second time");

            Glide.with(this).load(R.drawable.logo).into(ivLogo);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(BeginActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            },1100);
        }

    }
}