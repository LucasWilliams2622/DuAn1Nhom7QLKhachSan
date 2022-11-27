package com.example.duan1nhom7qlkhachsan.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1nhom7qlkhachsan.Fragment.AddRoomFragment;
import com.example.duan1nhom7qlkhachsan.Model.AppAdmin;
import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChangeNameActivity extends AppCompatActivity {
    private EditText edtOldName, edtNewName;
    private Button btnSaveChangeName;
    private AppAdmin appAdmin = null;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        edtOldName = findViewById(R.id.edtOldName);
        edtNewName = findViewById(R.id.edtNewName);
        btnSaveChangeName = findViewById(R.id.btnSaveChangeName);
        btnSaveChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateADmin(v);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        getDataAdmin();

    }

    public void onUpdateADmin(View view)
    {
        String nameAdmin = edtNewName.getText().toString();
        Map<String, Object> user = new HashMap<>();
        user.put("nameAdmin", nameAdmin);
        db.collection("admin")
                    .document(appAdmin.getIdAdmin())
                    .set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(ChangeNameActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            getDataAdmin();
                            appAdmin = null;
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ChangeNameActivity.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                        }
                    });
    }
//    db.collection("users").document(appUser.getUserId())
//            .set(item)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//        @Override
//        public void onSuccess(Void aVoid) {
//            onCanceUserlClick(null);
//            Toast.makeText(FirebaseUserActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
//            getData();
//            appUser=null;
//        }
//    })
//            .addOnFailureListener(new OnFailureListener() {
//        @Override
//        public void onFailure(@NonNull Exception e) {
//            Toast.makeText(FirebaseUserActivity.this, "Cập nhật khôngthành công", Toast.LENGTH_SHORT).show();
//        }
//    });





    public void getDataAdmin() {
        db.collection("admin")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppAdmin> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //String idAdmin,emailAdmin,nameAdmin,passwordAdmin,role;
                                Map<String, Object> map = document.getData();

                                String name = map.get("nameAdmin").toString();


                                AppAdmin appAdmin = new AppAdmin(name);
                                String nameAdmin = appAdmin.getNameAdmin();

                                edtOldName.setText(nameAdmin+"");
                                Log.d(">>>>>>>>>>>>>>>","ten admin" + name + "|" + nameAdmin);
                                appAdmin.setIdAdmin(document.getId());
                                list.add(appAdmin);
                                for (int i=0;i<list.size();i++)
                                {

                                    if (list.get(i).getNameAdmin().equals(map.get("nameAdmin").toString()))
                                    {
                                        Toast.makeText(ChangeNameActivity.this, "aaa", Toast.LENGTH_SHORT).show();
                                    }
                                }


//                                getSupportFragmentManager().beginTransaction()
//                                        .replace(R.id.flAddRoom, AddRoomFragment.newInstance(list)).commit();
                            }
                        }
                    }
                });
    }
//
//    public void onUpdateAdminClick(AppAdmin admin) {
//        edtNewName.setText(admin.getNameAdmin());
//        appAdmin = admin;
//
//    }
}