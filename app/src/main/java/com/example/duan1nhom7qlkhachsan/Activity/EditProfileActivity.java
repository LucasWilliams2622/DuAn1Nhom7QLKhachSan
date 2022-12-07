package com.example.duan1nhom7qlkhachsan.Activity;

import static com.example.duan1nhom7qlkhachsan.MainActivity.MY_REQUEST_CODE;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity implements IAdapterUserClickEvent {
    private EditText edtFullNameUser, edtEmailUser, edtPhoneNumberUser;
    private TextView tvProfileEdit;
    private Button btnUpdateAccount, btnDeleteAccount, btnBackToMainActivity;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private AppUser appUser = null;
    SharedPreferences sharedPreferForUser, sharedPreferences;
    String role;
    private ProgressDialog progressDialog;
    private ImageView ivEditProfileUser,edtAvatar;
    private MainActivity mMainActivity;
    View headerLayout;
    private Uri mUri;
    int SELECT_PICTURE = 200;
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
        edtAvatar = findViewById(R.id.edtAvatar);
        tvProfileEdit = findViewById(R.id.tvProfileEdit);
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
                onUpdateProfileUser();

            }
        });
        btnBackToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(EditProfileActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        edtAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });
        setProfileOfUser();

    }
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        ivEditProfileUser.setImageBitmap(
                                selectedImageBitmap);
                    }
                }
            });

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    ivEditProfileUser.setImageURI(selectedImageUri);
                }
            }
        }
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
            tvProfileEdit.setText("Password");
            edtPhoneNumberUser.setText("");
        } else {

            db.collection("user")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                ArrayList<AppUser> list = new ArrayList<>();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Map<String, Object> map = document.getData();
                                    String emailUser = map.get("emailUser").toString();
                                    String nameUser = map.get("nameUser").toString();
                                    String phoneNumUser = map.get("phoneNumUser").toString();

                                    appUser = new AppUser(nameUser, emailUser, phoneNumUser);
                                    edtFullNameUser.setText(nameUser);
                                    edtEmailUser.setText(emailUser);
                                    edtPhoneNumberUser.setText(phoneNumUser);
                                    appUser.setCodeUser(document.getId());

                                    //Test
                                    String a = appUser.getCodeUser();
                                    Log.d(">>>>>>>>>>>>>>>> onComplete Editròile", "     " + a);
                                    //end test
                                    list.add(appUser);
                                }
                            }
                        }
                    });
        }


    }

    public void onUpdateProfileUser() {
        String fullnameUser = edtFullNameUser.getText().toString().trim();
        String emailUser = edtEmailUser.getText().toString().trim();
        String phoneNumUser = edtPhoneNumberUser.getText().toString().trim();
        if (fullnameUser.equals("") || emailUser.equals("") || phoneNumUser.equals("")) {
            Toast.makeText(this, "Hãy nhập đủ thông tin", Toast.LENGTH_SHORT).show();

        } else {
            progressDialog.setTitle("Updating Profile ...");
            progressDialog.show();
            sharedPreferences = getSharedPreferences("AdminInfo", 0);
            role = sharedPreferences.getString("role", "");
            if (role.equals("admin")) {
                TextView fullNameUpdate = headerLayout.findViewById(R.id.tvNameUserLogin);
                TextView emailAdmin = headerLayout.findViewById(R.id.tvEmailUserLogin);
                fullNameUpdate.setText("Wellcom " + fullnameUser);
                emailAdmin.setText("" + emailUser);

            } else {
                Map<String, Object> user = new HashMap<>();
                user.put("nameUser", fullnameUser);
                user.put("emailUser", emailUser);
                user.put("phoneNumUser", phoneNumUser);
                Log.d(">>>>>>>>>", "fullnameUser" + fullnameUser);
                Log.d(">>>>>>>>>", "emailUser" + emailUser);
                Log.d(">>>>>>>>>", "phoneNumUser" + phoneNumUser);
//
//                NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
//                navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
//
//                View header=navigationView.getHeaderView(0);
//                /*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
//                TextView name = (TextView)header.findViewById(R.id.tvNameUserLogin);
//                TextView email = (TextView)header.findViewById(R.id.tvEmailUserLogin);
//                name.setText(fullnameUser);
//                email.setText(emailUser);

                if (appUser == null) {
                    progressDialog.dismiss();
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                } else {
                    db.collection("user")
                            .document(appUser.getCodeUser())
                            .set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    progressDialog.dismiss();
                                    Toast.makeText(EditProfileActivity.this, "Update Successful", Toast.LENGTH_SHORT).show();
                                    Log.d(">>>>>>>>>>>.", "Update  Successful");
                                    appUser = null;
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();

                                    Toast.makeText(EditProfileActivity.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }

        }
    }


    @Override
    public void onDeleteAccountClick(AppUser user) {

        String emailUser = edtEmailUser.getText().toString().trim();
        db.collection("user")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppUser> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Map<String, Object> map = document.getData();
                                String emailUserDB = map.get("emailUser").toString();
                                if (emailUserDB.equals(emailUser)) {

                                    new AlertDialog.Builder(EditProfileActivity.this)
                                            .setTitle("Xóa")
                                            .setMessage("Xóa sẽ không phục hồi được")
                                            .setNegativeButton("Hủy", null)
                                            .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                    db.collection("user").document(user.getCodeUser())
                                                            .delete()
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    Toast.makeText(EditProfileActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                                                    Intent i = new Intent(EditProfileActivity.this, BeginActivity.class);
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

                            }
                        }
                    }
                });

    }


//    private void openAlbum() {
//        if (mMainActivity == null) {
//            return;
//        }
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) { // < hon android 6
//            mMainActivity.openGallery();
//            return;
//        }
//        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//            mMainActivity.openGallery();
//        } else {
//            String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
//            requestPermissions(permission, MY_REQUEST_CODE);
//
//        }
//    }
}