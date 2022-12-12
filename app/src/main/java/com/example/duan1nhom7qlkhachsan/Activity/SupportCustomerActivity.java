package com.example.duan1nhom7qlkhachsan.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1nhom7qlkhachsan.Fragment.AddRoomFragment;
import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.Model.AppSupport;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SupportCustomerActivity extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    EditText edtNameCustomer, edtPhoneNumberCustomer, edtContentSupport;
    Button btnSupportCustomer, btnBackToMainActivity, btnCallSupport;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_customer);
        edtNameCustomer = findViewById(R.id.edtNameCustomer);
        edtPhoneNumberCustomer = findViewById(R.id.edtPhoneNumberCustomer);
        edtContentSupport = findViewById(R.id.edtContentSupport);
        btnSupportCustomer = findViewById(R.id.btnSupportCustomer);
        btnBackToMainActivity = findViewById(R.id.btnBackToMainActivity);
        btnCallSupport = findViewById(R.id.btnCallSupport);
        btnCallSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
        btnBackToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnSupportCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSeeSupport();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataAddSupport();
    }

    public void makePhoneCall() {
        String phoneNumber = "0337744148";

        if (ContextCompat.checkSelfPermission(SupportCustomerActivity.this
                , Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SupportCustomerActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE
                    }, REQUEST_CALL);

        } else {
//            String dial = "tel" + phoneNumber;
//            Intent callIntent = new Intent(Intent.ACTION_CALL);
//            callIntent.setData(Uri.parse(dial));
//            startActivity(callIntent);

            String phone = "+0337744148";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onSeeSupport() {
        String name = edtNameCustomer.getText().toString();
        String phone = edtPhoneNumberCustomer.getText().toString();
        String content = edtContentSupport.getText().toString();

        if (name.equals("") || phone.equals("") || content.equals("")) {
            Toast.makeText(this, "Hãy nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            // Create a new user with a first and last name
            Map<String, Object> support = new HashMap<>();
            support.put("nameCustomer", name);
            support.put("phoneNumCustomer", phone);
            support.put("contentSupport", content);

            db.collection("supportCustomer")
                    .add(support)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            edtNameCustomer.setText("");
                            edtPhoneNumberCustomer.setText("");
                            edtContentSupport.setText("");
                            Toast.makeText(SupportCustomerActivity.this, "Gửi thành công", Toast.LENGTH_SHORT).show();
                            getDataAddSupport();
                            //Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SupportCustomerActivity.this, "Gửi thất bại", Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }

    public void getDataAddSupport() {
        db.collection("supportCustomer")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppSupport> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String name = map.get("nameCustomer").toString();
                                String phone = map.get("phoneNumCustomer").toString();
                                String content = map.get("contentSupport").toString();

                                AppSupport appSupport = new AppSupport(-1, name, phone, content);
                                appSupport.setSupportId(document.getId());
                                list.add(appSupport);
                            }
                        }
                    }
                });
    }

}