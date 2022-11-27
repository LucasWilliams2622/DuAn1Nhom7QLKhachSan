package com.example.duan1nhom7qlkhachsan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    EditText edt_username;
    EditText edt_password;
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
        ImageView ivShowPass = findViewById(R.id.ivShowPass);

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
                                            Map<String, Object> map = document.getData();
                                            String passwordAdmin = map.get("passwordAdmin").toString();
                                            String emailAdmin = map.get("emailAdmin").toString();
                                            String nameAdmin = map.get("nameAdmin").toString();


                                            String username = edt_username.getText().toString();
                                            String password = edt_password.getText().toString();
//                                            Log.d(">>>>>>>>>>>>>","username"+username);
//                                            Log.d(">>>>>>>>>>>>>","password"+password);
                                            if (username.equals(emailAdmin.toString()) && password.equals(passwordAdmin.toString())) {
                                                Toast.makeText(LoginActivity.this, "Wellcome " + nameAdmin, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                            } else {
                                                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                                            }


                                        }


                                    } else {
                                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

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


//                    String id = oldProfile.getId();
//                    UserDao u = new UserDao(LoginActivity.this);
//                    u.register(id, "", 1);

                }
            }
        };
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject me, GraphResponse response) {

                                if (response.getError() != null) {
                                    // handle error
                                } else {

                                    String user_lastname = me.optString("last_name");
                                    String user_firstname = me.optString("first_name");
                                    String user_email = response.getJSONObject().optString("email");

                                    Log.d(">>>>>>>>", "email" + user_email);
                                    Log.d(">>>>>>>>", "name " + user_firstname + user_lastname);

                                    Map<String, Object> user = new HashMap<>();
                                    user.put("nameUser", user_firstname + user_lastname);
                                    user.put("emailUser", user_email);
                                    user.put("idRoom", "000");
                                    user.put("idUser", "USER"+user_firstname);
                                    user.put("phoneNumUser", "");
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


                                }
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "last_name,first_name,email");
                request.setParameters(parameters);
                request.executeAsync();
                Log.d(">>>>>>>>>>>>", "onSuccess" + loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d(">>>>>>>>>", "onError:" + exception.getMessage());
            }
        });

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
                        user.put("idRoom", "001");
                        user.put("idUser", "");
                        user.put("phoneNumUser", "099999");


                        Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
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
        Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(homeIntent);
        finish();
    }


    @Override
    protected void onDestroy() {
        profileTracker.stopTracking();
        super.onDestroy();
    }

    public void getData() {

    }
}