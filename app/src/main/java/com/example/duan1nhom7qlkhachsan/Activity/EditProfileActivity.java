package com.example.duan1nhom7qlkhachsan.Activity;

import static com.example.duan1nhom7qlkhachsan.MainActivity.MY_REQUEST_CODE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1nhom7qlkhachsan.MainActivity;
import com.example.duan1nhom7qlkhachsan.Model.AppUser;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity implements IAdapterUserClickEvent {
    private EditText edtFullNameUser, edtEmailUser, edtPhoneNumberUser;
    private Button btnUpdateAccount, btnDeleteAccount, btnBackToMainActivity;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private AppUser appUser = null;
    SharedPreferences sharedPreferForUser, sharedPreferences;
    String role;
    private ProgressDialog progressDialog;
    private ImageView ivEditProfileUser;
    private MainActivity mMainActivity;
    View headerLayout;
    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        edtFullNameUser = findViewById(R.id.edtFullNameUser);
        edtEmailUser = findViewById(R.id.edtEmailUser);
        edtPhoneNumberUser = findViewById(R.id.edtPhoneNumberUser);
        btnUpdateAccount = findViewById(R.id.btnUpdateAccount);
        btnDeleteAccount = findViewById(R.id.btnDeleteAccount);
        btnBackToMainActivity = findViewById(R.id.btnBackToMainActivity);
        ivEditProfileUser = findViewById(R.id.ivEditProfileUser);
        sharedPreferForUser = getSharedPreferences("UserInfo", 0);
        sharedPreferences = getSharedPreferences("AdminInfo", 0);

        progressDialog = new ProgressDialog(EditProfileActivity.this);
        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteAccountClick(appUser);
            }
        });
        btnUpdateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClickEditEmail();

            }
        });
        btnBackToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(EditProfileActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
       setProfileOfUser();

    }

    @Override
    protected void onResume() {
        super.onResume();
       setProfileOfUser();
    }

    public void setUri(Uri mUri) {
        this.mUri = mUri;
    }

    public void setBitmapImageView(Bitmap bitmapImageView) {
        ivEditProfileUser.setImageBitmap(bitmapImageView);
    }

    public void setProfileOfUser() {
        //ShảedPreferance for user
        String emailUser = sharedPreferForUser.getString("emailUser", "");
        String nameUser = sharedPreferForUser.getString("nameUser", "");
        String phoneNumUser = sharedPreferForUser.getString("phoneNumUser", "");

        //SharedPreferance for Admin
        String nameAdmin = sharedPreferences.getString("nameAdmin", "");
        String emailAdmin = sharedPreferences.getString("emailAdmin", "");


        String role = sharedPreferences.getString("role", "");
        if (role.equals("admin")) {
            edtFullNameUser.setText(nameAdmin);
            edtEmailUser.setText(emailAdmin);
            edtPhoneNumberUser.setText("");

        } else {
            edtFullNameUser.setText(nameUser);
            edtEmailUser.setText(emailUser);
            edtPhoneNumberUser.setText(phoneNumUser);
        }


    }
    private void onClickEditEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String newEmail = edtEmailUser.getText().toString().trim();
        progressDialog.setTitle("Chỉnh sửa Email...");
        progressDialog.show();
        user.updateEmail(newEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(mMainActivity, "Chỉnh sửa Email thành công !!!", Toast.LENGTH_SHORT).show();
                            mMainActivity.showProfile();
                        }else{
                            Toast.makeText(mMainActivity, "Chỉnh sửa Email thất bại !!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void onUpdateProfileUser() {
        Log.d(">>>>>>>>>>>>.", "Click");
        FirebaseUser userDB = FirebaseAuth.getInstance().getCurrentUser();

        String fullnameUser = edtFullNameUser.getText().toString().trim();
        String emailUser = edtEmailUser.getText().toString().trim();
        String phoneNumUser = edtPhoneNumberUser.getText().toString().trim();
        if (fullnameUser.equals("") || emailUser.equals("") || phoneNumUser.equals("")) {
            Toast.makeText(this, "Hãy nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            Log.d(">>>>>>>>>>>>.", "validation");

        } else {
            Log.d(">>>>>>>>>>>>.", "in");

            progressDialog.setTitle("Update Profile ...");
            progressDialog.show();
            sharedPreferences = getSharedPreferences("AdminInfo", 0);
            role = sharedPreferences.getString("role", "");
            if (role.equals("admin")) {
                TextView fullNameUpdate = headerLayout.findViewById(R.id.tvNameUserLogin);
                TextView emailAdmin = headerLayout.findViewById(R.id.tvEmailUserLogin);
                fullNameUpdate.setText("Wellcom " + fullnameUser);
                emailAdmin.setText("" + emailUser);

            } else {

//                TextView fullNameUpdate = headerLayout.findViewById(R.id.tvNameUserLogin);
//                TextView emailAdmin = headerLayout.findViewById(R.id.tvEmailUserLogin);
//                fullNameUpdate.setText("Wellcom " + fullnameUser);
//                emailAdmin.setText("" + emailUser);
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(fullnameUser)
                        .setPhotoUri(mUri)
                        .build();

                userDB.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Update Profile thành công !!!", Toast.LENGTH_SHORT).show();
                                    Log.d(">>>>>>>>>>>.", "Update  Successful");
                                }
                            }
                        });
                Map<String, Object> user = new HashMap<>();
                user.put("nameUser", fullnameUser);
                user.put("emailUser", emailUser);
                user.put("phoneNumUser", phoneNumUser);
                Log.d(">>>>>>>>>", "fullnameUser" + fullnameUser);
                Log.d(">>>>>>>>>", "emailUser" + emailUser);
                Log.d(">>>>>>>>>", "phoneNumUser" + phoneNumUser);

//                if (appUser == null) {
//                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
//                } else {
//                    db.collection("user")
//                            .document(appUser.getIdUser())
//                            .set(user)
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    //  progressDialog.dismiss();
//
//                                    Toast.makeText(EditProfileActivity.this, "Update Successful", Toast.LENGTH_SHORT).show();
//                                    Log.d(">>>>>>>>>>>.", "Update  Successful");
//
//                                    appUser = null;
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(EditProfileActivity.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                }
            }

        }
    }

    public void getUserData() {
        db.collection("user")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppUser> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                //private String idUser,nameUser,emailUser,phoneUser,idRoom;

                                Map<String, Object> map = document.getData();
                                String idUser = map.get("idUser").toString();
                                String nameUser = map.get("nameUser").toString();
                                String emailUser = map.get("emailUser").toString();
                                String phoneNumUser = map.get("phoneNumUser").toString();
                                String idRoom = map.get("idRoom").toString();

                                AppUser appUser = new AppUser(-1, idUser, nameUser, emailUser, phoneNumUser, idRoom);
//                                appUser.setIdUser((document.getId()));
                                list.add(appUser);

//                                Log.d(">>>>>>", "nameUser" + nameUser);
//                                Log.d(">>>>>>", "emailUser" + emailUser);
//                                Log.d(">>>>>>", "phoneUser" + phoneNumUser);

                                edtFullNameUser.setText(nameUser);
                                edtEmailUser.setText(emailUser);
                                edtPhoneNumberUser.setText(phoneNumUser);


                            }
                        }
                    }
                });
    }

    @Override
    public void onDeleteAccountClick(AppUser user) {
        new AlertDialog.Builder(EditProfileActivity.this)
                .setTitle("Xóa")
                .setMessage("Xóa sẽ không phục hồi được")
                .setNegativeButton("Hủy", null)
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.collection("user").document(user.getEmailUser())
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(EditProfileActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(EditProfileActivity.this, LoginActivity.class);
                                        startActivity(i);

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(EditProfileActivity.this, "Xóa khong thành công", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                })
                .show();
    }

    private void clickAvatar() {
        ivEditProfileUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlbum();
            }
        });


        btnUpdateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickEditEmail();
            }
        });
    }

    private void openAlbum() {
        if (mMainActivity == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) { // < hon android 6
            mMainActivity.openGallery();
            return;
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            mMainActivity.openGallery();
        } else {
            String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, MY_REQUEST_CODE);

        }
    }
}