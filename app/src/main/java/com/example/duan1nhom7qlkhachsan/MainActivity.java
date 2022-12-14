package com.example.duan1nhom7qlkhachsan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.example.duan1nhom7qlkhachsan.Activity.AddRoomActivity;
import com.example.duan1nhom7qlkhachsan.Activity.AddServiceActivity;
import com.example.duan1nhom7qlkhachsan.Activity.BookedRoomActivity;
import com.example.duan1nhom7qlkhachsan.Activity.BookedServiceActivity;
import com.example.duan1nhom7qlkhachsan.Activity.CheckOutActivity;
import com.example.duan1nhom7qlkhachsan.Activity.EditProfileActivity;
import com.example.duan1nhom7qlkhachsan.Activity.GioiThieuActivity;
import com.example.duan1nhom7qlkhachsan.Activity.HandleSupportRequestActivity;
import com.example.duan1nhom7qlkhachsan.Activity.LaudryActivity;
import com.example.duan1nhom7qlkhachsan.Activity.LoginActivity;

import com.example.duan1nhom7qlkhachsan.Activity.BookRoomActivity;
import com.example.duan1nhom7qlkhachsan.Activity.SupportCustomerActivity;

import com.example.duan1nhom7qlkhachsan.Fragment.DoanhThuFragment;

import com.example.duan1nhom7qlkhachsan.Fragment.TrangChuFragment;

import com.example.duan1nhom7qlkhachsan.Test.TestActivity;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final int MY_REQUEST_CODE = 10;
    SharedPreferences sharedPreferences, sharedPreferForUser;
    String role;
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    GoogleSignInClient gsc;
    GoogleSignInAccount account;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView tvNameUserLogin, tvEmailUserLogin;
    private ImageView ivAvatarHeader;
    NavigationView navigationView;
    View headerLayout;
    Toolbar toolBar;
    GoogleApiClient mGoogleApiClient;

    final private EditProfileActivity mNguoiDungFragment = new EditProfileActivity();
    final private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        if (intent == null) {
                            return;
                        }
                        // set anh len profile
                        Uri uri = intent.getData();
                        mNguoiDungFragment.setUri(uri);
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            mNguoiDungFragment.setBitmapImageView(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.DrawerLayout);
        frameLayout = findViewById(R.id.frameLayout);
        toolBar = findViewById(R.id.toolBar);
        navigationView = findViewById(R.id.navigationView);
        headerLayout = navigationView.getHeaderView(0);

        tvNameUserLogin = headerLayout.findViewById(R.id.tvNameUserLogin);
        tvEmailUserLogin = headerLayout.findViewById(R.id.tvEmailUserLogin);

        //SahredPreferance for admin
        sharedPreferences = getSharedPreferences("AdminInfo", 0);
        String nameAdmin = sharedPreferences.getString("nameAdmin", "");
        String emailAdmin = sharedPreferences.getString("emailAdmin", "");

        //SahredPreferance for user
        sharedPreferForUser = getSharedPreferences("UserInfo", 0);
        String nameUser = sharedPreferForUser.getString("nameUser", "");
        String emailUser = sharedPreferForUser.getString("emailUser", "");

        Log.d(">>>>>>>>>>>>>>>>", "nameAdimin" + nameAdmin);
        Log.d(">>>>>>>>>>>>>>>>", "registerEmailAdmin" + emailAdmin);

        Log.d(">>>>>>>>>>>>>>>>", "emailUser" + emailUser);
        Log.d(">>>>>>>>>>>>>>>>", "emailUser" + emailUser);

        role = sharedPreferences.getString("role", "");
        if (role.equals("admin")) {
            tvNameUserLogin.setText(nameAdmin);
            tvEmailUserLogin.setText(emailAdmin);

        } else {
            tvNameUserLogin.setText(nameUser);
            tvEmailUserLogin.setText(emailUser);
        }

        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(MainActivity.this, gso);
        account = GoogleSignIn.getLastSignedInAccount(MainActivity.this);
//        showProfile();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {

                    case R.id.mDichVu:
                        Intent intentService = new Intent(MainActivity.this, LaudryActivity.class);
                        startActivity(intentService);
                        break;
                    case R.id.mDichVuDaDat:
                        Intent intentBookedService = new Intent(MainActivity.this, BookedServiceActivity.class);
                        startActivity(intentBookedService);
                        break;
                    case R.id.mTraPhong:
                        Intent intentCheckOut = new Intent(MainActivity.this, CheckOutActivity.class);
                        startActivity(intentCheckOut);
                        break;
                    case R.id.mDatPhong:
                        Intent itentBookRoom = new Intent(MainActivity.this, BookRoomActivity.class);
                        startActivity(itentBookRoom);
                        break;
                    case R.id.mDoanhThu:
                        fragment = new DoanhThuFragment();
                        break;
                    case R.id.mHotro:
                        Intent itentSupportCus = new Intent(MainActivity.this, HandleSupportRequestActivity.class);
                        startActivity(itentSupportCus);
                        break;
                    case R.id.mTuVanKH:
                        Intent itentSupport = new Intent(MainActivity.this, SupportCustomerActivity.class);
                        startActivity(itentSupport);
                        break;
                    case R.id.mPhongDaDat:
                        Intent itentBookedRoom = new Intent(MainActivity.this, BookedRoomActivity.class);
                        startActivity(itentBookedRoom);
                        break;
                    case R.id.mTrangChu:
                        fragment = new TrangChuFragment();
                        break;
                    case R.id.mGioiThieuSavila:
                        Intent intentGioiThieuSavile = new Intent(MainActivity.this, GioiThieuActivity.class);
                        startActivity(intentGioiThieuSavile);
                        break;
                    case R.id.mAddRoom:
                        Intent intentAddRoom = new Intent(MainActivity.this, AddRoomActivity.class);
                        startActivity(intentAddRoom);
                        break;
                    case R.id.mAddService:
                        Intent intentAddService = new Intent(MainActivity.this, AddServiceActivity.class);
                        startActivity(intentAddService);
                        break;
                    case R.id.mEditProfile:
                        Intent intentProfile = new Intent(MainActivity.this, EditProfileActivity.class);
                        startActivity(intentProfile);
                        break;

                    case R.id.mTest:
                        Intent intentmTest = new Intent(MainActivity.this, TestActivity.class);
                        startActivity(intentmTest);
                        break;
                    case R.id.mDangXuat:
                        //Logout google
                        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                                new ResultCallback<Status>() {
                                    @Override
                                    public void onResult(Status status) {
                                        // ...
                                        Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_SHORT).show();
                                        Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                                        //Sau khi out khỏi account sẽ trở về màn hình điện thoại, không trở về main
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(i);
                                    }
                                });
                        LoginManager.getInstance().logOut();
//                        if (account != null) {
//                            gsc.signOut().addOnCompleteListener(MainActivity.this,
//                                    new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            Intent homeIntent = new Intent(MainActivity.this, LoginActivity.class);
//                                            startActivity(homeIntent);
//                                            finish();
//                                        }
//                                    });
//                        } else {
//                            Log.d(">>>>>", "amdin");
//                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
////                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(intent);
//                        }
                    default:
                        Intent intentGioiThieuSavilee = new Intent(MainActivity.this, GioiThieuActivity.class);
                        startActivity(intentGioiThieuSavilee);
                        break;
                }
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, fragment)
                            .commit();
                    toolBar.setTitle(item.getTitle());
                }


                drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });

        sharedPreferences = getSharedPreferences("AdminInfo", 0);
        role = sharedPreferences.getString("role", "");
        Log.d(">>>>>>>>>>>", "role " + role);
        if (!role.equals("admin")) {
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.mDoanhThu).setVisible(false);
            menu.findItem(R.id.mTraPhong).setVisible(false);
            menu.findItem(R.id.mTest).setVisible(false);

            menu.findItem(R.id.mAddRoom).setVisible(false);
            menu.findItem(R.id.mAddService).setVisible(false);
            menu.findItem(R.id.mHotro).setVisible(false);
        }
    }
//
//    public void openSupportFragment(){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.frameLayout, new HoTroFragment())
//                .commit();
//    }
//    public void openSupportFragmentHotro(){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.frameLayout, new SupportCustomerFragment())
//                .commit();
//    }

//    public void showProfile() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user == null) {
//            return;
//        }
//        String name = user.getDisplayName();
//        String email = user.getEmail();
//        Uri photoUrl = user.getPhotoUrl();
//
//        if (name == null) {
//            tvNameUserLogin.setVisibility(View.GONE);
//        } else {
//            tvNameUserLogin.setVisibility(View.VISIBLE);
//            tvNameUserLogin.setText(name);
//        }
//        tvNameUserLogin.setText(name);
//        tvEmailUserLogin.setText(email);
//        // Glide.with(this).load(photoUrl).error(R.drawable.ic_avatar).into(img_avatar);
//    }

    public void openGallery() { // mo thu vien de chon anh
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(i, "Select Picture"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Vui lòng cho phép cấp quền truy cập !!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}