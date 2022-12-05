package com.example.duan1nhom7qlkhachsan.Test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.duan1nhom7qlkhachsan.R;

public class TestActivity extends AppCompatActivity {
ImageView imageView;
Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        imageView = findViewById(R.id.imageView);
        animation = AnimationUtils.loadAnimation( getApplicationContext(),
                R.anim.animation_scale);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(animation);
            }
        });
    }
}