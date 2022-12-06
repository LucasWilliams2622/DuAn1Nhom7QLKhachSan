package com.example.duan1nhom7qlkhachsan.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.duan1nhom7qlkhachsan.MainActivity;
import com.example.duan1nhom7qlkhachsan.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    long check = 1;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;
    private static final String EMAIL = "email";
    EditText edt_username, edt_password;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferForUser;
    ImageView ivShowPass;
    CheckBox chkSavePassword;

    TextView tvNameUserLogin;
    //Google
    GoogleSignInClient gsc;

    //Facebook
    LoginButton loginButton;

    private LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_username = findViewById(R.id.edt_username_login);
        edt_password = findViewById(R.id.edt_password_lgoin);
        Button btn_login = findViewById(R.id.btn_login);
        Button btn_register = findViewById(R.id.btnGoRegister);
        ivShowPass = findViewById(R.id.ivShowPass);
        chkSavePassword = findViewById(R.id.chkSavePassword);
        chkSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(LoginActivity.this, "Đã lưu mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });
        sharedPreferences = getSharedPreferences("AdminInfo", 0);
        sharedPreferForUser = getSharedPreferences("UserInfo", 0);
        //Show Password
        edt_username.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        //Hide Password
        edt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_username.getText().toString();
                String password = edt_password.getText().toString();
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();

                } else {
                    db.collection("admin")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        List<String> list = new ArrayList<>();


                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            list.add(document.getId());
                                            //    private String idAdmin,emailAdmin,nameAdmin,passwordAdmin,role;
                                            SharedPreferences.Editor editorAdmin = sharedPreferences.edit();
                                            editorAdmin.putString("role", "admin");

                                            Map<String, Object> map = document.getData();
                                            String passwordAdmin = map.get("passwordAdmin").toString();
                                            String emailAdmin = map.get("emailAdmin").toString();
                                            String nameAdmin = map.get("nameAdmin").toString();
                                            editorAdmin.putString("nameAdmin", nameAdmin);
                                            editorAdmin.putString("emailAdmin", emailAdmin);
                                            Log.d(">>>>>>>>>>","nameAdmin in Login"+nameAdmin);
                                            Log.d(">>>>>>>>>>","emailAdmin in Login"+emailAdmin);
                                            editorAdmin.apply();
                                            //Login by account Admin
                                            String username = edt_username.getText().toString();
                                            String password = edt_password.getText().toString();

                                            String registerEmailAdmin = sharedPreferences.getString("emailAdmin", "");
                                            String registerPasswordAdmin = sharedPreferences.getString("passwordAdmin", "");

                                            if ((username.equals(registerEmailAdmin) && password.equals(registerPasswordAdmin)) || (username.equals(emailAdmin) && password.equals(passwordAdmin))) {
                                                Toast.makeText(LoginActivity.this, "Wellcome " + nameAdmin, Toast.LENGTH_SHORT).show();

                                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                            }
                                        }
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Login failed !!!", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });

        /*
         *Start: Đăng nhâp bằng Google
         * Project:https://console.firebase.google.com/project/myproject262-4a887/overview
         * Created:11/14/2022
         * By: Lucas
         *
         * */
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(LoginActivity.this, gso);
        // kiểm tra login google
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(LoginActivity.this);
        if (account != null) {
            Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(homeIntent);
            finish();
        }
        ImageView sib = findViewById(R.id.btnGoogle);
        sib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent googleIntent = gsc.getSignInIntent();
                googleLauncher.launch(googleIntent);//hientai khoan dang login trong may
            }
        });


        /*
         *End: Đăng nhâp bằng Google*/



        /*Start: Đăng nhâp bằng Facebook*/
        callbackManager = CallbackManager.Factory.create();
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(@Nullable Profile oldProfile, @Nullable Profile newProfile) {
                if (newProfile != null) {
                    Log.d(">>>>>>>>TAB", "onCurrentProfileChanged" + newProfile.getName());
                    Log.d(">>>>>>>>TAB", "onCurrentProfileChanged" + newProfile.getId());

                }
            }
        };
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration


    }


    // nhận kết quả Google SignIn
    ActivityResultLauncher<Intent> googleLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    try {
                        Log.d(">>>>>>>>>>>>", "Login Google");
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        String email = account.getEmail();
                        String displayName = account.getDisplayName();
                        // lưu database (tự làm)
                        //check neu email chua co trong db thi them
                        //neu co thi bao loi
//                        UserDao userDao = new UserDao(LoginActivity.this);
//                        userDao.register(email, null, 1);
                        Map<String, Object> user = new HashMap<>();
                        user.put("nameUser", displayName);
                        user.put("emailUser", email);
                        user.put("idRoom", "");
                        user.put("idUser", "USER" + email.toLowerCase().substring(1, 5) + email.toUpperCase().lastIndexOf(1));
                        user.put("phoneNumUser", "");

                        //SharedPreFerances
                        SharedPreferences.Editor editor = sharedPreferForUser.edit();
                        editor.putString("emailUser", email);
                        editor.putString("nameUser", displayName);
                        editor.putString("phoneNumUser", "");
                        editor.apply();
                        SharedPreferences.Editor editorAdmin = sharedPreferences.edit();
                        editorAdmin.putString("role", "user");
                        editorAdmin.apply();

                        //end saving by sharedPreferances
                        // Add a new document with a generated ID

                        db.collection("user")
                                .add(user)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                        //Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

                                    }
                                });


                        // chuyển màn hình qua Home
                        Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(homeIntent);
                        finish();

                        Log.d(">>>>>TAG", "onActivityResult: " + displayName);
                        Log.d(">>>>>TAG", ">>>: " + email);

                    } catch (Exception e) {
                        Log.d(">>>>>TAG", "onActivityResult error: " + e.getMessage());
                    }
                }
            }
    );


    //nhận kết quả của fb login
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences.Editor editorAdmin = sharedPreferences.edit();
        editorAdmin.putString("role", "user");
        editorAdmin.apply();
        Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(homeIntent);
        finish();
    }


    @Override
    protected void onDestroy() {
        profileTracker.stopTracking();
        super.onDestroy();
    }

    public void ShowHidePass(View view) {
        if (view.getId() == R.id.ivShowPass) {
            if (edt_password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.hide_password);
                //Show Password
                edt_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.show_password);
                //Hide Password
                edt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }

    public void getData() {

    }
}