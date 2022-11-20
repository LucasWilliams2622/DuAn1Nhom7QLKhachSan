package com.example.duan1nhom7qlkhachsan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1nhom7qlkhachsan.Model.AppAdmin;
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

public class RegisterActivity extends AppCompatActivity {
    private EditText edtEmailAdmin, edtNameAdmin, edtPasswordAdmin, edtIdAdmin, edtRoleAdmin;
    private Button btnRegister;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtEmailAdmin = findViewById(R.id.edtEmailAdmin);
        edtNameAdmin = findViewById(R.id.edtNameAdmin);
        edtPasswordAdmin = findViewById(R.id.edtPasswordAdmin);
        edtIdAdmin = findViewById(R.id.edtIdAdmin);
        edtRoleAdmin = findViewById(R.id.edtRoleAdmin);


        btnRegister = findViewById(R.id.btnRegister);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    public void getData() {
        db.collection("admin")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppAdmin> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
//                                private String idAdmin,emailAdmin,nameAdmin,passwordAdmin,role;

                                String idAdmin = map.get("idAdmin").toString();
                                String emailAdmin = map.get("emailAdmin").toString();
                                String nameAdmin = map.get("nameAdmin").toString();
                                String passwordAdmin = map.get("passwordAdmin").toString();
                                String role = map.get("role").toString();


                                AppAdmin admin = new AppAdmin(idAdmin, emailAdmin, nameAdmin, passwordAdmin, role);
                                admin.setIdAdmin(document.getId());
                                list.add(admin);
                            }
                            getSupportFragmentManager()
                                    .beginTransaction()
//                                    .replace(R.id.flRoom, DatPhongFragment.newInstance(list))
                                    .commit();
                        }
                    }
                });
    }

    public void onMoveToLogin(View view) {
        Intent moveLogin = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(moveLogin);
    }

    public void onRegisterClick(View view) {

        // EditText    edtEmailAdmin,edtNameAdmin,edtPasswordAdmin,edtIdAdmin,edtRoleAdmin;
        String emailAdmin = edtEmailAdmin.getText().toString();
        String nameAdmin = edtNameAdmin.getText().toString();
        String passwordAdmin = edtPasswordAdmin.getText().toString();
        String idAdmin = edtIdAdmin.getText().toString();
        String role = edtRoleAdmin.getText().toString();

        if (emailAdmin.equals(null) || nameAdmin.equals(null) || passwordAdmin.equals(null) || idAdmin.equals(null) || role.equals(null)) {
            Toast.makeText(this, "Vui lòng điền đủ thông tin !", Toast.LENGTH_SHORT).show();
        } else {
            // Create a new user with a first and last name
            Map<String, Object> user = new HashMap<>();
            user.put("idAdmin", idAdmin);
            user.put("emailAdmin", emailAdmin);
            user.put("nameAdmin", nameAdmin);
            user.put("passwordAdmin", passwordAdmin);
            user.put("role", role);

            // Add a new document with a generated ID
            db.collection("admin")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(RegisterActivity.this, "Registed", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(i);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this, "Faied registed", Toast.LENGTH_SHORT).show();
                        }
                    });

        }

    }
}