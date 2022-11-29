package com.example.duan1nhom7qlkhachsan.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1nhom7qlkhachsan.Fragment.AddRoomFragment;
import com.example.duan1nhom7qlkhachsan.MainActivity;
import com.example.duan1nhom7qlkhachsan.Model.AppAdmin;
import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.Model.AppUser;
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

public class EditProfileActivity extends AppCompatActivity implements IAdapterUserClickEvent {
    private EditText edtFullNameUser, edtEmailUser, edtPhoneNumberUser;
    private Button btnUpdateAccount, btnDeleteAccount, btnBackToMainActivity;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private AppUser appUser = null;
    SharedPreferences sharedPreferForUser;
//    ActivityUpdateDataBinding
//    DatabaseReferance
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
        sharedPreferForUser = getSharedPreferences("UserInfo", 0);

        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteAccountClick(appUser);
            }
        });
        btnUpdateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(EditProfileActivity.this, "Update User Successful", Toast.LENGTH_SHORT).show();
                Log.d(">>>>>>>>>>>.", "Update User Successful");
            }
        });
        btnBackToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(EditProfileActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

//        getUserData();

        getLoginUserData();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        getUserData();
    }

    public void getLoginUserData() {
        String emailUser = sharedPreferForUser.getString("emailUser", "");
        String nameUser = sharedPreferForUser.getString("nameUser", "");
        String phoneNumUser = sharedPreferForUser.getString("phoneNumUser", "");
//                Log.d(">>>>>>>>>>>>>>>>>>>>", "emailUser" + emailUser);
//                Log.d(">>>>>>>>>>>>>>>>>>>>", "nameUser" + nameUser);
//                Log.d(">>>>>>>>>>>>>>>>>>>>", "phoneNumUser" + phoneNumUser);
        edtFullNameUser.setText(nameUser);
        edtEmailUser.setText(emailUser);
        edtPhoneNumberUser.setText(phoneNumUser);
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

                                AppUser appUser = new AppUser(-1, nameUser, emailUser, phoneNumUser, idRoom);
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
                        db.collection("user").document(String.valueOf(user.getIdUser()))
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




}