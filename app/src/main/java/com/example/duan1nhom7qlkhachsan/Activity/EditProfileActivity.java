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

import com.bumptech.glide.Glide;
import com.example.duan1nhom7qlkhachsan.MainActivity;
import com.example.duan1nhom7qlkhachsan.Model.AppAdmin;
import com.example.duan1nhom7qlkhachsan.Model.AppUser;
import com.example.duan1nhom7qlkhachsan.R;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity implements IAdapterUserClickEvent {
    private EditText edtFullNameUser, edtEmailUser, edtPhoneNumberUser;
    private TextView tvProfileEdit;
    private Button btnUpdateAccount, btnDeleteAccount, btnBackToMainActivity, btnUpdateImage;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private AppUser appUser = null;
    private AppAdmin appAdmin = null;
    GoogleApiClient mGoogleApiClient;
    //update image
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    CircleImageView ivEditProfileUser;
    Uri selectedImage;
    private ImageView edtAvatar;
    String idUser, nameUser, emailUser, phoneNumUser, idRoom, codeUser;
//    private String idUser,nameUser,emailUser,phoneNumUser,idRoom, codeUser,imageUrl;

    //end update image
    SharedPreferences sharedPreferForUser, sharedPreferences;
    String role;
    private ProgressDialog progressDialog;
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
        btnUpdateImage = findViewById(R.id.btnUpdateImage);
        ivEditProfileUser = findViewById(R.id.ivEditProfileUser);
        edtAvatar = findViewById(R.id.edtAvatar);
        auth = FirebaseAuth.getInstance();
        tvProfileEdit = findViewById(R.id.tvProfileEdit);
        sharedPreferForUser = getSharedPreferences("UserInfo", 0);
        sharedPreferences = getSharedPreferences("AdminInfo", 0);
        progressDialog = new ProgressDialog(EditProfileActivity.this);


//    private String idUser,nameUser,emailUser,phoneNumUser,idRoom, codeUser,imageUrl;
        idUser = getIntent().getStringExtra("idUser");
        nameUser = getIntent().getStringExtra("nameUser");
        emailUser = getIntent().getStringExtra("emailUser");
        phoneNumUser = getIntent().getStringExtra("phoneNumUser");
        idRoom = getIntent().getStringExtra("idRoom");
        codeUser = getIntent().getStringExtra("codeUser");


//        btnUpdateImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (selectedImage != null) {
//                    StorageReference reference = storage.getReference().child("user").child(auth.getCurrentUser().getPhoneNumber());
//                    reference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                            if (task.isSuccessful()) {
//                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                    @Override
//                                    public void onSuccess(Uri uri) {
//
//                                        String imageUrl = uri.toString();
//
////    private String idUser,nameUser,emailUser,phoneNumUser,idRoom, codeUser,imageUrl;
//
//                                        AppUser addNewUser = new AppUser(idUser, nameUser, emailUser, phoneNumUser, idRoom, imageUrl);
//
//                                        database.getReference()
//                                                .child("user")
//
//                                                .setValue(addNewUser)
//                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                    @Override
//                                                    public void onSuccess(Void aVoid) {
//                                                        // dialog.dismiss();
//                                                        Intent intent = new Intent(EditProfileActivity.this, LoginActivity.class);
//                                                        Toast.makeText(EditProfileActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
//                                                        startActivity(intent);
//                                                        finish();
//                                                    }
//                                                });
//
//                                    }
//                                });
//                            }
//                        }
//                    });
//                } else {
//
//
//                }
//            }
//        });
        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("AdminInfo", 0);
                role = sharedPreferences.getString("role", "");
                if (role.equals("admin")) {
                    onDeleteAccountAdmin(appAdmin);
                } else {
                    onDeleteAccountClick(appUser);

                }
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
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");

        // pass the constant to compare it
        // with the returned requestCode

        //startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
        startActivityForResult(i, 45);
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
                        } catch (IOException e) {
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

            db.collection("admin")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                ArrayList<AppAdmin> list = new ArrayList<>();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Map<String, Object> map = document.getData();
                                    String nameAdmin = map.get("nameAdmin").toString();
                                    String emailAdmin = map.get("emailAdmin").toString();

                                    String passwordAdmin = map.get("passwordAdmin").toString();

                                    appAdmin = new AppAdmin(nameAdmin, emailAdmin, passwordAdmin);
                                    edtFullNameUser.setText(nameAdmin);
                                    edtEmailUser.setText(emailAdmin);
                                    edtPhoneNumberUser.setText(passwordAdmin);
                                    appAdmin.setCodeAdmin(document.getId());
                                    //Test
                                    String a = appAdmin.getCodeAdmin();
                                    Log.d(">>>>>>>>>>>>>>>> onComplete Editròile", "     " + a);
                                    //end test
                                    list.add(appAdmin);
                                }
                            }
                        }
                    });
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
//                TextView fullNameUpdate = headerLayout.findViewById(R.id.tvNameUserLogin);
//                    TextView emailAdmin = headerLayout.findViewById(R.id.tvEmailUserLogin);
//                fullNameUpdate.setText("Wellcom " + fullnameUser);
//                emailAdmin.setText("" + emailUser);
                Map<String, Object> user = new HashMap<>();
                user.put("nameAdmin", fullnameUser);
                user.put("emailAdmin", emailUser);
                user.put("passwordAdmin", phoneNumUser);
                user.put("idAdmin", "ADMIN001");
                user.put("role", "admin");

                Log.d(">>>>>>>>>", "nameAdmin" + fullnameUser);
                Log.d(">>>>>>>>>", "emailAdmin" + emailUser);
                Log.d(">>>>>>>>>", "passwordAdmin" + phoneNumUser);
                if (appAdmin == null) {
                    progressDialog.dismiss();
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                } else {
                    db.collection("admin")
                            .document(appAdmin.getCodeAdmin())
                            .set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    progressDialog.dismiss();
                                    Toast.makeText(EditProfileActivity.this, "Update admin  Successful", Toast.LENGTH_SHORT).show();
                                    Log.d(">>>>>>>>>>>.", "Update  Successful");
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

            } else {
                String idUser = sharedPreferForUser.getString("idUser", "");

                Map<String, Object> user = new HashMap<>();
                user.put("nameUser", fullnameUser);
                user.put("emailUser", emailUser);
                user.put("phoneNumUser", phoneNumUser);
                user.put("idRoom", "");
                user.put("idUser", idUser);

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

    public void onDeleteAccountAdmin(AppAdmin admin) {
        String emailUser = edtEmailUser.getText().toString().trim();

        db.collection("admin")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppAdmin> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Map<String, Object> map = document.getData();
                                String emailUserDB = map.get("emailAdmin").toString();
                                if (emailUserDB.equals(emailUser)) {

                                    new AlertDialog.Builder(EditProfileActivity.this)
                                            .setTitle("Xóa tài khoản")
                                            .setMessage("Xóa sẽ không phục hồi được")
                                            .setNegativeButton("Hủy", null)
                                            .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                    db.collection("admin").document(admin.getCodeAdmin())
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
                                                                    Toast.makeText(EditProfileActivity.this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
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
                                            .setTitle("Xóa tài khoản")
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
//                                                                     onLogoutClick();
                                                                    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                                                                            new ResultCallback<Status>() {
                                                                                @Override
                                                                                public void onResult(Status status) {
                                                                                    // ...
                                                                                    Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_SHORT).show();
                                                                                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                                                                    //Sau khi out khỏi account sẽ trở về màn hình điện thoại, không trở về main
                                                                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                                    startActivity(i);
                                                                                }
                                                                            });
                                                                    LoginManager.getInstance().logOut();
                                                                }
                                                            })
                                                            .addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    Toast.makeText(EditProfileActivity.this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
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

    public void onLogoutClick() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.AUTH_ID))
                .requestEmail()
                .build();

        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(EditProfileActivity.this, googleSignInOptions);
        googleSignInClient.signOut();
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