package com.example.duan1nhom7qlkhachsan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.duan1nhom7qlkhachsan.MainActivity;
import com.example.duan1nhom7qlkhachsan.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import javax.xml.transform.Result;


public class LoginActivity extends AppCompatActivity {
    ImageView btnFB;
    CallbackManager callbackManager;
    long check = 1;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    LoginButton loginManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText edt_username = findViewById(R.id.edt_username_login);
        EditText edt_password = findViewById(R.id.edt_password_lgoin);
        Button btn_login = findViewById(R.id.btn_login);
        Button btn_register = findViewById(R.id.btnGoRegister);
        ImageView ivShowPass = findViewById(R.id.ivShowPass);// mốt để toàn cục nha
        loginManager = findViewById(R.id.login_fb);


//        ThuThuDAO thuThuDAO = new ThuThuDAO(this);
       // btnFB = findViewById(R.id.login_fb);
//        btnFB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));
//            }
//        });
        callbackManager = CallbackManager.Factory.create();
        loginManager.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        startActivity(new Intent(LoginActivity.this,LoginActivity.class));
                        finish();
                    }


                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_username.getText().toString();
                String password = edt_password.getText().toString();

//                if (thuThuDAO.checkLogin(username, password)) {
//                    //save shareprefferences
//                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
//                    //truyen 2 gia tri 1 la ten sharepreferences va MODE
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("matt", username);
//                    editor.commit();
//
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//
//                } else {
//                    Toast.makeText(LoginActivity.this, "Username or password is wrong !!!", Toast.LENGTH_SHORT).show();
 //   //                if (thuThuDAO.checkLogin(username, password)) {
//                    //save shareprefferences
//                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
//                    //truyen 2 gia tri 1 la ten sharepreferences va MODE
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("matt", username);
//                    editor.commit();
//
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//
//                } else {
//                    Toast.makeText(LoginActivity.this, "Username or password is wrong !!!", Toast.LENGTH_SHORT).show();
 //               }
                getData();

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
               startActivity(intent);

            }
        });

        ivShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check++;
                if (check % 2 == 0) {
                    edt_password.setInputType(1);
                    Log.d(">>>>>>>>>", "check " + check);
                } else {
                    //edt_password.setInputType();

                    //  edt_password.setInputType(Integer.parseInt("textPassword"));
                }
            }
        });
    }

    public void getData(){
        db.collection("admin")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                list.add(document.getId());
                            }

                            Toast.makeText(LoginActivity.this, "Wellcome", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent Data){
        callbackManager.onActivityResult(requestCode,resultCode,Data);
        super.onActivityResult(requestCode,resultCode,Data);
    }

}