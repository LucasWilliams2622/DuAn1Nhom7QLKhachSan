package com.example.duan1nhom7qlkhachsan.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1nhom7qlkhachsan.Fragment.MapsFragment;
import com.example.duan1nhom7qlkhachsan.MainActivity;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

public class GioiThieuActivity extends AppCompatActivity {
    private Button btnShowLocation;
    FusedLocationProviderClient mfusedLocationProviderClient;
    Location mLastLocation;
    TextView tvLocation;
    Button btnBackToMainActivity, btnCloseMap;
    CardView cardViewMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu);
        tvLocation = findViewById(R.id.tvLocation);
        btnCloseMap = findViewById(R.id.btnCloseMap);
        cardViewMap = (CardView) findViewById(R.id.cardViewMap);
        btnCloseMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewMap.setVisibility(View.INVISIBLE);
                cardViewMap.setLayoutParams(new LinearLayoutCompat.LayoutParams(0, 0));
            }
        });
        btnBackToMainActivity = findViewById(R.id.btnBackToMainActivity);
        btnBackToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GioiThieuActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        btnShowLocation = findViewById(R.id.btnShowLocation);
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewMap.setVisibility(View.VISIBLE);
                cardViewMap.setLayoutParams(new LinearLayoutCompat.LayoutParams(1060, 570));
                onShowMap(v);

            }
        });
    }

    public void onShowMap(View view) {
        String address = "10.853690, 106.627342";
        //g???i fragment l??n use getSupportFragmentM??nger
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flMap, MapsFragment.newInstance(address))
                .commit();
    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //y??U c???u ng?????i d??ng c???p quy???n truy c???p location khi ch??a ??c ph??p
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 9999);
        } else {
            //th???c hi???n vi???c l???y Location khi ???? c?? quy???n
            mfusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        mLastLocation = location;
                        String thongtin = mLastLocation.getLatitude() + " , " + mLastLocation.getLongitude();
                        tvLocation.setText(thongtin);
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.flMap, MapsFragment.newInstance(thongtin))
                                .commit();

                    } else {
                        tvLocation.setText("H??ng l???y ???????c v??? tr??");
                    }
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 9999) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(this, "T??? ch???i", Toast.LENGTH_SHORT).show();
            }
        }
    }
}