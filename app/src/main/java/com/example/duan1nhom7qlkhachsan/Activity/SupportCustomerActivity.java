package com.example.duan1nhom7qlkhachsan.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    EditText edtNameCustomer, edtPhoneNumberCustomer, edtContentSupport;
    Button btnSupportCustomer, btnBackToMainActivity;
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