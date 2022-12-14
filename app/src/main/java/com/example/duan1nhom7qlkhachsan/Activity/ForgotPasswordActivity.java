package com.example.duan1nhom7qlkhachsan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.duan1nhom7qlkhachsan.R;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText edtResetEmail;
    private Button btn_resetPass, btnBackToMainActivity;
//    ActivityForgotPassword2Binding
    // ProcessDialog
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initView();
    }

    private void initView() {
        edtResetEmail = findViewById(R.id.edtResetEmail);
        btn_resetPass = findViewById(R.id.btn_resetPass);
        btnBackToMainActivity = findViewById(R.id.btnBackToMainActivity);


    }
}